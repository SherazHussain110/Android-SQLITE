package sheraz.dataappsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    EditText editName;
    EditText editFname;
    EditText editPhone;
    EditText editEmail;
    Button btnAdd;
    UserContacts userContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        databaseHandler = new DatabaseHandler(this);

        editName = (EditText) findViewById(R.id.user_name);
        editFname = (EditText) findViewById(R.id.f_name);
        editPhone = (EditText) findViewById(R.id.phone);
        editEmail = (EditText) findViewById(R.id.email);
        btnAdd = (Button) findViewById(R.id.addData);

        userContacts = new UserContacts();

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(editName.getText().toString().equals("") || editFname.getText().toString().equals("") || editPhone.getText().toString().equals("") || editEmail.getText().toString().equals(""))
                {
                    Toast.makeText(AddDataActivity.this,"Fill The Empty Fields...",Toast.LENGTH_LONG).show();
                }
                else
                {
                    userContacts.setUserName(editName.getText().toString());
                    userContacts.setFatherName(editFname.getText().toString());
                    userContacts.setPhoneNumber(editPhone.getText().toString());
                    userContacts.setEmailAddress(editEmail.getText().toString());

                    databaseHandler.addContact(userContacts);

                    Toast.makeText(AddDataActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                    Intent dataListIntent = new Intent(AddDataActivity.this,DataListActivity.class);
                    startActivity(dataListIntent);
                }
            }

        });

    }
}
