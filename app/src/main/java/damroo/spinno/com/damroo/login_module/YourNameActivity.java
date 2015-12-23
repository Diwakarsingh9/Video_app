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

public class YourNameActivity extends Activity {

    @Bind(R.id.continuebtn)Button continuebtn;
    @Bind(R.id.firstname)AnyEditTextView firstname;
    @Bind(R.id.lastname)AnyEditTextView lastname;


    public static  Activity activity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_name);
        ButterKnife.bind(this);
        activity = this ;
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstname.getText().toString().equals("")  ||   lastname.getText().toString().equals("")){
                    Toast.makeText(YourNameActivity.this , "Please enter the valid details." , Toast.LENGTH_SHORT).show();
                }else {
                    LoginConstants.FirstName_temp = firstname.getText().toString();
                    LoginConstants.LastName_temp = lastname.getText().toString();
                    startActivity(new Intent(YourNameActivity.this , YourBirthdayActivity.class));
                }

            }
        });
    }

}
