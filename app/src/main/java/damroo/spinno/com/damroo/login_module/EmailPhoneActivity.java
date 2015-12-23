package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ctrlplusz.anytextview.AnyEditTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

public class EmailPhoneActivity extends Activity {



    @Bind(R.id.email)AnyEditTextView  email ;
    @Bind(R.id.phonenumber)AnyEditTextView  phonenumber ;



    @Bind(R.id.getstarted)Button getstarted ;
    public static  Activity activity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_phone);
        ButterKnife.bind(this);

        activity = this ;

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmailValid(email.getText().toString())){
                    LoginConstants.Email_temp = email.getText().toString();
                    LoginConstants.Password_temp = phonenumber.getText().toString() ;
                    startActivity(new Intent(EmailPhoneActivity.this , YourNameActivity.class));
                }else {
                    Toast.makeText(EmailPhoneActivity.this , "Please enter a valid email address" , Toast.LENGTH_SHORT).show();
                }


            }
        });
    }



    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }



}
