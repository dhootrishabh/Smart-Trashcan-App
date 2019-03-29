package e.rdhoot.trashcanmonitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrashCanDetails extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref;
    Long v;
    TextView clevel;
    TextView clat;
    TextView clong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_can_details);

        clevel = findViewById(R.id.canlevel);
        clat = findViewById(R.id.canlat);
        clong = findViewById(R.id.canlong);

        myref = database.getReference("TrashCans/can1001");

        myref.child("Level").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                v = dataSnapshot.getValue(Long.class);
                clevel.setText(v.toString());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TrashCanDetails.this, " Error ", Toast.LENGTH_SHORT).show();
            }
        });
        myref.child("Latitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                v = dataSnapshot.getValue(Long.class);
                clat.setText(v.toString());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TrashCanDetails.this, " Error ", Toast.LENGTH_SHORT).show();
            }
        });
        myref.child("Longitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                v = dataSnapshot.getValue(Long.class);
                clong.setText(v.toString());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TrashCanDetails.this, " Error ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
