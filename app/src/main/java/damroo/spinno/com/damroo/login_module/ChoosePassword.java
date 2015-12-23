package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ctrlplusz.anytextview.AnyEditTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

public class ChoosePassword extends Activity {


    @Bind(R.id.continue_btn)Button continue_btn ;
    @Bind(R.id.password)AnyEditTextView password ;
    @Bind(R.id.repassword)AnyEditTextView repassword ;

    public static  Activity activity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_password);
        ButterKnife.bind(this);
        activity = this ;

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(password.getText().toString().equals("") || repassword.getText().toString().equals("")){
                    Toast.makeText(ChoosePassword.this, "Please Enter Password" , Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals(repassword.getText().toString())){
                    LoginConstants.Password_temp = password.getText().toString();
                    startActivity(new Intent(ChoosePassword.this , GenderActivity.class));
                }else {
                    Toast.makeText(ChoosePassword.this, "Password Doesn't Matches" , Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
