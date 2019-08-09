package com.example.designresource;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DrawFragment extends Fragment {

    private static final String KEY_POSITION = "POSITION";
    private int positionCurrent = -1;

    public static DrawFragment getInstance(int position) {
        DrawFragment fragment = new DrawFragment();
        Bundle argument = new Bundle();
        argument.putInt(KEY_POSITION, position);
        fragment.setArguments(argument);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null)
            positionCurrent = savedInstanceState.getInt(KEY_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_draw, container, false);
        RelativeLayout relativeLayout = rootView.findViewById(R.id.rect);
        Bundle argument = getArguments();
        if (argument != null)
            relativeLayout.addView(new Draw(getActivity(), argument.getInt(KEY_POSITION)));
        else if (positionCurrent != -1)
            relativeLayout.addView(new Draw(getActivity(), positionCurrent));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, positionCurrent);
    }

    class Draw extends View {
        private List<PositionObject> listObjects = new ArrayList<>();
        private PositionObject drawFree;
        private Paint paint = new Paint();
        private int forms;
        private Path path = new Path();
        private int id = 0;

        public Draw(Context context, int forms) {
            super(context);
            this.forms = forms;
            String[] classEvent = getResources().getStringArray(R.array.event_draw);
            Toast.makeText(context, classEvent[forms], Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            listObjects.forEach(
                    it -> {
                        switch (forms) {
                            case 0:
                                canvas.drawCircle(it.getX(), it.getY(), 50, paint);
                                break;
                            case 1:
                                canvas.drawOval(it.getX(), it.getY(), it.getX() + 150, it.getY() + 75, paint);
                                break;
                            case 2:
                                canvas.drawRect(it.getX(), it.getY(), it.getX() + 150, it.getY() + 150, paint);
                                break;
                            case 3:
                                canvas.drawRect(it.getX(), it.getY(), it.getX() + 200, it.getY() + 100, paint);
                                break;
                        }
                    }
            );
            //fro drawing free only = 4
            if (forms == 4) {
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(20);
                paint.setColor(Color.BLACK);
                if (id == 1)
                    path.moveTo(drawFree.getX(), drawFree.getY());
                else if (id == 2)
                    path.lineTo(drawFree.getX(), drawFree.getY());
                id = 0;
                canvas.drawPath(path, paint);
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && forms != 4) {
                listObjects.add(new PositionObject(event.getX(), event.getY()));
            } else if (event.getAction() == MotionEvent.ACTION_DOWN && forms == 4) {
                drawFree = new PositionObject(event.getX(), event.getY());
                id = 1;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE && forms == 4) {
                drawFree = new PositionObject(event.getX(), event.getY());
                id = 2;
            }
            invalidate();
            return true;
        }
    }


}
