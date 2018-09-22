package daluobo.cnbetamobile.view;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemLineDecoration extends RecyclerView.ItemDecoration {

    private int color;//decoration color
    private int thick; //decoration thick size
    private int dashWidth;//decoration dash with
    private int dashGap;//decoration dash gap
    private int padding;//padding of start and end

    private Paint paint;

    public ItemLineDecoration(int color, int thick, int dashWidth, int dashGap, int padding) {
        this.color = color;
        this.thick = thick;
        this.dashWidth = dashWidth;
        this.dashGap = dashGap;
        this.padding = padding;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thick);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.set(0, 0, 0, thick);

        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childrenCount = parent.getChildCount();

        if (dashWidth == 0 && dashGap == 0) {
            for (int i = 0; i < childrenCount; i++) {
                if (i != childrenCount - 1) {
                    View childView = parent.getChildAt(i);
                    int myY = childView.getBottom() + thick / 2;
                    c.drawLine(0, myY, parent.getWidth(), myY, paint);
                }
            }
        } else {
            PathEffect effects = new DashPathEffect(new float[]{0, 0, dashWidth, thick}, dashGap);
            paint.setPathEffect(effects);

            for (int i = 0; i < childrenCount; i++) {
                if (i != childrenCount - 1) {
                    View childView = parent.getChildAt(i);
                    int myY = childView.getBottom() + thick / 2;

                    Path path = new Path();
                    path.moveTo(padding, myY);
                    path.lineTo(parent.getWidth() - padding, myY);

                    c.drawPath(path, paint);
                }

            }
        }
    }
}
