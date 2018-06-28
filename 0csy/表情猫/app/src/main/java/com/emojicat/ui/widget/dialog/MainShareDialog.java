package com.emojicat.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emojicat.R;
import com.emojicat.ui.widget.recyclerview.utils.Utils;

/**
 * Created by chenshouyin on 17/4/24.
 */

public class MainShareDialog extends Dialog implements View.OnClickListener{
    private ImageView ivImage;
    private ImageView ivShareWeChat;
    private ImageView ivShareQQ;
    private ImageView ivShareCollection;
    private ImageView ivShareMore;
    private TextView tvText;
    private View rootView,viewFrameLayout;
    private Context mContext;
    private String[] args;//url,text,postion
    private DialogShareClick mDialogShareClick;
    public interface DialogShareClick{
        void doClickImage();
        void doClickWeChat();
        void doClickQQ();
        void doClickCollection();
        void doClickMore();
        void setShareView(View view);
    }
    public MainShareDialog(@NonNull Context context,String[] args,DialogShareClick mDialogShareClick) {
        //super(context);
        super(context, R.style.MyDialog);//设置透明样式
        this.mContext = context;
        this.mDialogShareClick = mDialogShareClick;
        this.args = args;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_main_share, null);
        rootView = layout.findViewById(R.id.rootView);
        ivImage = (ImageView) layout.findViewById(R.id.ivImage);
        ivShareWeChat = (ImageView) layout.findViewById(R.id.ivShareWeChat);
        ivShareQQ = (ImageView) layout.findViewById(R.id.ivShareQQ);
        ivShareCollection = (ImageView) layout.findViewById(R.id.ivShareCollection);
        ivShareMore = (ImageView) layout.findViewById(R.id.ivShareMore);
        tvText = (TextView) layout.findViewById(R.id.tvText);
        viewFrameLayout = layout.findViewById(R.id.viewFrameLayout);
        rootView.setOnClickListener(this);
        ivImage.setOnClickListener(this);
        ivShareWeChat.setOnClickListener(this);
        ivShareQQ.setOnClickListener(this);
        ivShareCollection.setOnClickListener(this);
        ivShareMore.setOnClickListener(this);

        Glide.with(Utils.getContext())
                .load(args[0])
                //.placeholder(R.mipmap.iv_defualt)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(ivImage);
        tvText.setText(args[1]);
        this.setContentView(layout);
    }


    @Override
    public void onClick(View v) {

        if (v==rootView){

        }else if (v==ivImage){
            if (mDialogShareClick!=null){
                mDialogShareClick.doClickImage();
            }
        }else if (v==ivShareWeChat){
            if (mDialogShareClick!=null){
                mDialogShareClick.setShareView(viewFrameLayout);
                mDialogShareClick.doClickWeChat();
            }
        }else if (v==ivShareQQ){
            if (mDialogShareClick!=null){
                mDialogShareClick.setShareView(viewFrameLayout);
                mDialogShareClick.doClickQQ();
            }
        }else if (v==ivShareCollection){
            if (mDialogShareClick!=null){
                mDialogShareClick.doClickCollection();
            }
        }else if (v==ivShareMore){
            if (mDialogShareClick!=null){
                mDialogShareClick.doClickMore();
            }
        }
        dismiss();
    }
}
