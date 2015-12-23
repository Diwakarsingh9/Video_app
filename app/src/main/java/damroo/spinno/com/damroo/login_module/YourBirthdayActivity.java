package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

public class YourBirthdayActivity extends Activity {

    @Bind(R.id.continuebtn) Button continuebtn ;
    @Bind(R.id.birdaylayout) LinearLayout birdaylayout ;
    @Bind(R.id.birthdaytext) TextView birthdaytext ;
    public static  Activity activity ;


    private int year;
    private int month;
    private int day;
    static final int DATE_PICKER_ID = 1111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_birthday);
        ButterKnife.bind(this);
        activity = this ;

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);


        birdaylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_PICKER_ID);
            }
        });


        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginConstants.DateOfBirth_temp = birthdaytext.getText().toString();
                startActivity(new Intent(YourBirthdayActivity.this , ChoosePassword.class));
            }
        });


    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            birthdaytext.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1)
                    .append("-").append(year)
                    .append(" "));
        }
    };

}
