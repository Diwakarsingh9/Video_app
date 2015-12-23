package damroo.spinno.com.damroo.home;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctrlplusz.anytextview.AnyEditTextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

public class QuestionActivity extends AppCompatActivity {
    @Bind(R.id.question)TextView question ;
    @Bind(R.id.rupees)TextView rupees ;
    @Bind(R.id.rupees2)TextView rupees2 ;
    @Bind(R.id.a)TextView  a ;
    @Bind(R.id.llforquestion11)LinearLayout llforquestion ;
    @Bind(R.id.llforrupees)LinearLayout  llforrupees ;
    @Bind(R.id.b)TextView  b ;
    @Bind(R.id.c)TextView  c ;
    @Bind(R.id.heart_img_btn)ImageView heart ;
    @Bind(R.id.share)ImageView  share ;
    @Bind(R.id.cart)ImageView  cart ;
    @Bind(R.id.heart_img_btn11)ImageView heart11 ;
    @Bind(R.id.share11)ImageView  share11 ;
    @Bind(R.id.cart11)ImageView  cart11 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        llforquestion.setVisibility(View.VISIBLE);
        llforrupees.setVisibility(View.GONE);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setTextColor(getResources().getColor(R.color.client_offwhite_color));
                a.setBackgroundColor(getResources().getColor(R.color.client_green_color));
                b.setTextColor(getResources().getColor(R.color.client_black_colour));
                b.setBackgroundColor(getResources().getColor(R.color.client_offwhite_color));
                c.setTextColor(getResources().getColor(R.color.client_black_colour));
                c.setBackgroundColor(getResources().getColor(R.color.client_offwhite_color));
                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .playOn(findViewById(R.id.llforquestion11));
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        rupees.setText("Rs 150");
                        llforquestion.setVisibility(View.GONE);
                        llforrupees.setVisibility(View.VISIBLE);

                    }
                }, 1200);


            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setTextColor(getResources().getColor(R.color.client_offwhite_color));
                b.setBackgroundColor(getResources().getColor(R.color.client_red_color));
                a.setTextColor(getResources().getColor(R.color.client_black_colour));
                a.setBackgroundColor(getResources().getColor(R.color.client_offwhite_color));
                c.setTextColor(getResources().getColor(R.color.client_black_colour));
                c.setBackgroundColor(getResources().getColor(R.color.client_offwhite_color));
               // YoYo.with(Techniques.FadeIn).duration(2000).delay(100).playOn(llforquestion);
                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .playOn(findViewById(R.id.llforquestion11));
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        rupees.setText("Rs 0");
                        llforquestion.setVisibility(View.GONE);
                        llforrupees.setVisibility(View.VISIBLE);

                    }
                }, 1200);

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setTextColor(getResources().getColor(R.color.client_offwhite_color));
                c.setBackgroundColor(getResources().getColor(R.color.client_red_color));
                a.setTextColor(getResources().getColor(R.color.client_black_colour));
                a.setBackgroundColor(getResources().getColor(R.color.client_offwhite_color));
                b.setTextColor(getResources().getColor(R.color.client_black_colour));
                b.setBackgroundColor(getResources().getColor(R.color.client_offwhite_color));
                //YoYo.with(Techniques.FadeIn).duration(2000).delay(100).playOn(llforquestion);
                YoYo.with(Techniques.FadeOut)
                        .duration(1000)
                        .playOn(findViewById(R.id.llforquestion11));
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {
                        rupees.setText("Rs 0");
                        llforquestion.setVisibility(View.GONE);
                        llforrupees.setVisibility(View.VISIBLE);

                    }
                }, 1200);

            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.setImageResource(R.drawable.heartliked);
                cart.setImageResource(R.drawable.cart);
                share.setImageResource(R.drawable.linkw);
            }
        });
        heart11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart11.setImageResource(R.drawable.heartliked);
                cart11.setImageResource(R.drawable.cart);
                share11.setImageResource(R.drawable.linkw);
            }
        });
    }



}
