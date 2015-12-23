package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

public class SignInTerms extends Activity {


     @Bind(R.id.term_text)TextView term_text ;
     @Bind(R.id.getstart)
     Button getstart ;
    public static  Activity activity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_terms);

        activity = this ;

        ButterKnife.bind(this);


        term_text.setText(Html.fromHtml(getString(R.string.your_text)));



        getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInTerms.this, EmailPhoneActivity.class));
            }
        });



    }
}
