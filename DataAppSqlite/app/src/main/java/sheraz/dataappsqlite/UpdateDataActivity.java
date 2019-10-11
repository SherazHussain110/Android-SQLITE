package sheraz.dataappsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataActivity extends AppCompatActivity {

    EditText updateId,updateName,updateFname,updatePhone,updateEmail;
    Button btnUpdate;
    DatabaseHandler db;
    UserContacts userContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);


        updateId = (EditText) findViewById(R.id.idUpdate);
        updateName = (EditText) findViewById(R.id.user_nameUpdate);
        updateFname = (EditText) findViewById(R.id.f_nameUpdate);
        updatePhone= (EditText) findViewById(R.id.phoneUpdate);
        updateEmail = (EditText) findViewById(R.id.emailUpdate);
        btnUpdate = (Button) findViewById(R.id.updateData);

        //UpdateData();
        db = new DatabaseHandler(this);
        userContacts = new UserContacts();

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("ID");
        String name = bundle.getString("Name",null);
        String fName = bundle.getString("Father Name",null);
        String phone = bundle.getString("Phone Number",null);
        String email = bundle.getString("Email",null);

        updateId.setText(id+"");
        updateName.setText(name);
        updateFname.setText(fName);
        updatePhone.setText(phone);
        updateEmail.setText(email);

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db.updateContact(new UserContacts(Integer.parseInt(updateId.getText().toString()),updateName.getText().toString(),
                        updateFname.getText().toString(),updatePhone.getText().toString(),updateEmail.getText().toString()));

                Toast.makeText(UpdateDataActivity.this,"Data Updated...",Toast.LENGTH_LONG).show();

                Intent dataListIntent = new Intent(UpdateDataActivity.this,DataListActivity.class);
                startActivity(dataListIntent);
            }
        });

    }
}
