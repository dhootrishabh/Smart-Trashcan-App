package e.rdhoot.trashcanmonitor;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText valueText;
    TextView textData;
    DatabaseReference myref;
    String v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueText = findViewById(R.id.value);
        textData = findViewById(R.id.dataValue);

        myref = database.getReference("level");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                v = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, "Value : " + v, Toast.LENGTH_SHORT).show();
                textData.setText(v);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, " Error ", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
