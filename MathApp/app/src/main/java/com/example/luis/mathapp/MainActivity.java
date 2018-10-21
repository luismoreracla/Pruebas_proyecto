package com.example.luis.mathapp;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView drop1, drop2, drop3, numerador, denominador,left,right;
    ImageView div, mult;

    int first_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drop1=(TextView)findViewById(R.id.drop1);
        drop2=(TextView)findViewById(R.id.drop2);
        drop3=(TextView)findViewById(R.id.drop3);
        left=(TextView)findViewById(R.id.left);
        right=(TextView)findViewById(R.id.right);
        numerador=(TextView)findViewById(R.id.numerador);
        denominador=(TextView)findViewById(R.id.denominador);
        div=(ImageView)findViewById(R.id.division);
        mult=(ImageView)findViewById(R.id.multiplicacion);


        div.setOnLongClickListener(longClickListener);
        mult.setOnLongClickListener(longClickListener);
        drop1.setOnDragListener(dragListener);
        drop2.setOnDragListener(dragListener);
        drop3.setOnDragListener(dragListener);
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data=ClipData.newPlainText("","");
            View.DragShadowBuilder myShadow= new View.DragShadowBuilder(v);
            v.startDrag(data, myShadow, v, 0);
            return true;
        }
    };


    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent=event.getAction();
            final View view = (View) event.getLocalState();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:

                    if (view.getId() == R.id.division || view.getId() == R.id.multiplicacion) {
                        drop2.setText("");
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                 
                    if(view.getId()==R.id.division) {
                        numerador.setVisibility(View.VISIBLE);
                        denominador.setVisibility(View.VISIBLE);
                    }
                    else if(view.getId()==R.id.multiplicacion) {
                        numerador.setVisibility(View.VISIBLE);
                    }
                    if(first_time!=0){
                        numerador.append("()");
                        denominador.append("()");
                    }
                    first_time++;
                    System.out.println(first_time);
                    break;
            }
            return true;
        }
    };

}
