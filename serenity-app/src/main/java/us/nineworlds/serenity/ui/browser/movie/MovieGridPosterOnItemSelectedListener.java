/**
 * The MIT License (MIT)
 * Copyright (c) 2012 David Carver
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF
 * OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package us.nineworlds.serenity.ui.browser.movie;

import javax.inject.Inject;

import us.nineworlds.plex.rest.PlexappFactory;
import us.nineworlds.serenity.R;
import us.nineworlds.serenity.core.imageloader.SerenityBackgroundLoaderListener;
import us.nineworlds.serenity.core.imageloader.SerenityImageLoader;
import us.nineworlds.serenity.core.model.VideoContentInfo;
import us.nineworlds.serenity.injection.BaseInjector;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemSelectedListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

/**
 * When a poster is selected, update the information displayed in the browser.
 *
 * @author dcarver
 *
 */
public class MovieGridPosterOnItemSelectedListener extends BaseInjector
implements OnItemSelectedListener {

	private static Activity context;
	private View previous;
	private TwoWayAdapterView<?> adapter;

	@Inject
	SerenityImageLoader serenityImageLoader;

	@Inject
	PlexappFactory plexFactory;

	private final ImageLoader imageLoader;

	public MovieGridPosterOnItemSelectedListener() {
		super();

		imageLoader = serenityImageLoader.getImageLoader();
	}

	@Override
	public void onItemSelected(TwoWayAdapterView<?> av, View v, int position,
			long id) {
		if (v == null) {
			return;
		}

		context = (Activity) v.getContext();

		adapter = av;
		changeBackgroundImage();

		if (previous != null) {
			previous.setPadding(0, 0, 0, 0);
		}

		previous = v;

		v.setPadding(5, 5, 5, 5);

		createMovieMetaData();
	}

	private void changeBackgroundImage() {
		VideoContentInfo videoInfo = (VideoContentInfo) adapter
				.getSelectedItem();

		if (videoInfo.getBackgroundURL() == null) {
			return;
		}

		View fanArt = context.findViewById(R.id.fanArt);
		String transcodingURL = plexFactory.getImageURL(
				videoInfo.getBackgroundURL(), 1280, 720);
		imageLoader
		.loadImage(transcodingURL, new ImageSize(1280, 720),
				new SerenityBackgroundLoaderListener(fanArt,
						R.drawable.movies));

	}

	private void createMovieMetaData() {

		VideoContentInfo mi = (VideoContentInfo) adapter.getSelectedItem();
		TextView subt = (TextView) context.findViewById(R.id.subtitleFilter);
		subt.setVisibility(View.GONE);
		Spinner subtitleSpinner = (Spinner) context
				.findViewById(R.id.videoSubtitle);
		subtitleSpinner.setVisibility(View.GONE);

		TextView posterTitle = (TextView) context
				.findViewById(R.id.movieActionBarPosterTitle);
		posterTitle.setText(mi.getTitle());
	}

	@Override
	public void onNothingSelected(TwoWayAdapterView<?> av) {
		if (previous != null) {
			previous.setPadding(0, 0, 0, 0);
			previous.refreshDrawableState();
		}

	}
}
