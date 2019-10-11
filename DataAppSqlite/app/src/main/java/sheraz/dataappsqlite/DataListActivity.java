package sheraz.dataappsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class DataListActivity extends AppCompatActivity {

    DatabaseHandler db;
    List<UserContacts> list;
    ListView listView;
    AlertDialog.Builder builder;
    UserContacts userContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);


        db = new DatabaseHandler(this);
        userContacts = new UserContacts();

        listView = (ListView) findViewById(R.id.list);
        list = db.getAllContacts();
        ArrayAdapter<UserContacts> adapter = new ArrayAdapter<UserContacts>(this,android.R.layout.simple_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        builder = new AlertDialog.Builder(getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idOne = list.get(position).getId();
                String name = list.get(position).getUserName();
                String fName = list.get(position).getFatherName();
                String phone = list.get(position).getPhoneNumber();
                String email = list.get(position).getEmailAddress();


                UserContacts contact = new UserContacts();
                contact.setId(idOne);
                contact.setUserName(name);
                contact.setFatherName(fName);
                contact.setPhoneNumber(phone);
                contact.setEmailAddress(email);

                showMessage(view,contact);
            }
        });
    }

    public void showMessage(final View view,final UserContacts contact)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete OR Update");
        builder.setMessage("Click to delete or update the data");

        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                int userId = contact.getId();
                String userName = contact.getUserName();
                String fatherName = contact.getFatherName();
                String phone = contact.getPhoneNumber();
                String email = contact.getEmailAddress();

                Intent intent = new Intent(DataListActivity.this , UpdateDataActivity.class);
                intent.putExtra("ID",userId);
                intent.putExtra("Name" , userName);
                intent.putExtra("Father Name" ,fatherName);
                intent.putExtra("Phone Number" ,phone);
                intent.putExtra("Email",email);
                startActivity(intent);
                //Toast.makeText(ListActivity.this,"Well Done",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("DELETE", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                db.deleteContact(contact);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
