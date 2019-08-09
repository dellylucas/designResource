package com.example.designresource;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class DrawAdapter extends BaseAdapter {

    private List<ModeDraw> listDraw;
    private Context context;

    DrawAdapter(List<ModeDraw> listDraw, Context context) {
        this.listDraw = listDraw;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDraw.size();
    }

    @Override
    public ModeDraw getItem(int position) {
        return listDraw.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inf = LayoutInflater.from(context);
            view = inf.inflate(R.layout.list_adapter, parent, false);
        }
        TextView tittle = view.findViewById(R.id.textView);
        TextView description = view.findViewById(R.id.textView2);

        tittle.setText(getItem(position).getTittle());
        description.setText(getItem(position).getDescription());

        RelativeLayout relativeLayout = view.findViewById(R.id.imageObject);
        relativeLayout.removeAllViews();
        relativeLayout.addView(new DrawObject(context, position));

        return view;
    }

    class DrawObject extends View {
        private Paint paint = new Paint();
        private int position;

        public DrawObject(Context context, int position) {
            super(context);
            this.position = position;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(context.getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
            paint.setAntiAlias(true);
            float width = getWidth();
            float height = getHeight();

            switch (position) {
                case 0:
                    canvas.drawCircle(width / 2, height / 2, 60, paint);
                    break;
                case 1:
                    canvas.drawOval(5, 10, width - 10, height - 40, paint);
                    break;
                case 2:
                    canvas.drawRect(10, 10, width - 10, height - 10, paint);
                    break;
                case 3:
                    canvas.drawRect(5, 15, width - 5, height - 30, paint);
                    break;
                case 4:
                    //start code
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(8);
                    int hMargin = 100 / 9;
                    int vMargin = 100 / 4;
                    Point a = new Point((111 / 2), 20);
                    Point b = new Point((111 / 2 + hMargin), (20 + vMargin));
                    Point c = new Point((111), (20 + vMargin));
                    Point d = new Point((111 / 2 + 2 * hMargin), (120 / 2 + vMargin / 2));
                    Point e = new Point((111 / 2 + 3 * hMargin), (120));
                    Point f = new Point((111 / 2), (120 - vMargin));
                    Point g = new Point((111 / 2 - 3 * hMargin), (120));
                    Point h = new Point((111 / 2 - 2 * hMargin), (120 / 2 + vMargin / 2));
                    Point i = new Point(11, (20 + vMargin));
                    Point j = new Point((111 / 2 - hMargin), (20 + vMargin));
                    Path path = new Path();
                    path.moveTo(a.x, a.y);
                    path.lineTo(b.x, b.y);
                    path.lineTo(c.x, c.y);
                    path.lineTo(d.x, d.y);
                    path.lineTo(e.x, e.y);
                    path.lineTo(f.x, f.y);
                    path.lineTo(f.x, f.y);
                    path.lineTo(g.x, g.y);
                    path.lineTo(h.x, h.y);
                    path.lineTo(i.x, i.y);
                    path.lineTo(j.x, j.y);
                    path.lineTo(a.x, a.y);
                    path.close();
                    canvas.drawPath(path, paint);
                    break;


            }

        }

    }
}
