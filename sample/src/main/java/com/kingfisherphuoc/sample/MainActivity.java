package com.kingfisherphuoc.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kingfisherphuoc.quickactiondialog.AlignmentFlag;
import com.kingfisherphuoc.quickactiondialog.QuickActionDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View buttonShow = findViewById(R.id.btnShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySampleDialogFragment = new MySampleDialogFragment();
                mySampleDialogFragment.setAnchorView(buttonShow);
                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                mySampleDialogFragment.show(getSupportFragmentManager(), null);
            }
        });
    }

    private MySampleDialogFragment mySampleDialogFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // must dismiss this dialog before orientation change to avoid AnchorView is deleted!
        if (mySampleDialogFragment != null && mySampleDialogFragment.isVisible()) {
            mySampleDialogFragment.dismiss();
        }
        super.onSaveInstanceState(outState);
    }

    public static class MySampleDialogFragment extends QuickActionDialogFragment {

        @Override
        protected int getArrowImageViewId() {
            return R.id.ivArrow;
//            return 0; that mean you donot have an arrow
        }

        @Override
        protected int getLayout() {
            return R.layout.dialog_sample_view;
        }

        @Override
        protected boolean isStatusBarVisible() {
            return true;
        }


        @Override
        protected boolean isCanceledOnTouchOutside() {
            return true;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            view.findViewById(R.id.btnSample).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Button inside Dialog!!", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }


}
