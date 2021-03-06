package daluobo.cnbetamobile.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Keep;
import android.support.annotation.StringRes;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import daluobo.cnbetamobile.R;


/**
 * Created by daluobo on 2016/10/29.
 */
@Keep
public class MenuBadgeProvider extends ActionProvider {
    private ImageView mIvIcon;
    private TextView mTvBadge;

    private int clickWhat;
    private OnClickListener onClickListener;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public MenuBadgeProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_badge, null, false);
        view.setLayoutParams(layoutParams);
        mIvIcon = view.findViewById(R.id.icon);
        mTvBadge = view.findViewById(R.id.badge);
        view.setOnClickListener(onViewClickListener);
        return view;
    }

    private View.OnClickListener onViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onClickListener != null)
                onClickListener.onClick(clickWhat);
        }
    };

    /**
     * 设置点击监听。
     *
     * @param what            what。
     * @param onClickListener listener。
     */
    public void setOnClickListener(int what, OnClickListener onClickListener) {
        this.clickWhat = what;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int what);
    }

    /**
     * 设置图标。
     *
     * @param icon drawable 或者mipmap中的id。
     */
    public void setIcon(@DrawableRes int icon) {
        mIvIcon.setImageResource(icon);
    }

    /**
     * 设置显示的数字。
     *
     * @param i 数字。
     */
    public void setBadge(int i) {
        mTvBadge.setText(Integer.toString(i));
    }

    /**
     * 设置文字。
     *
     * @param i string.xml中的id。
     */
    public void setTextInt(@StringRes int i) {
        mTvBadge.setText(i);
    }

    /**
     * 设置显示的文字。
     *
     * @param i 文字。
     */
    public void setText(CharSequence i) {
        mTvBadge.setText(i);
    }
}
