/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woocommerce.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.woocommerce.R;


public class ScreenSlidePageFragment extends Fragment {

    String TAG ="Message: ";
    static final String ARG_PAGE = "ARG_PAGE";
    static final String IMG_LINK = "IMG_LINK";



    private int mPage;
    View view;
    public String tabId = "Tags";

    public static ScreenSlidePageFragment newInstance(int page, String imagelink) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString("IMG_LINK", imagelink);
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);





        String img = getArguments().getString(IMG_LINK);
        System.out.println("hellothere2"+ img);


        ImageView imageView = (ImageView) view.findViewById(R.id.sliderImage);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.productimage3));

        return view;
    }







}