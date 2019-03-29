package e.rdhoot.trashcanmonitor;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllViewActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    TextView can;
    Long v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_view);

        can = findViewById(R.id.can1001);

        String id = can.getResources().getResourceName(can.getId());
        id = id.split("/")[1];
        String path = "TrashCans/" + id;
        myRef = db.getReference(path);
        myRef.child("Level").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                v = dataSnapshot.getValue(Long.class);
                String level = v.toString();
                if(Integer.parseInt(level) == 0){
                    Toast.makeText(AllViewActivity.this, "Color is Green", Toast.LENGTH_SHORT).show();
                    can.setTextColor(Color.GREEN);
                }
                if(Integer.parseInt(level) == 50){
                    Toast.makeText(AllViewActivity.this, "Color is Yellow", Toast.LENGTH_SHORT).show();
                    can.setTextColor(Color.YELLOW);
                }
                if(Integer.parseInt(level) == 75){
                    Toast.makeText(AllViewActivity.this, "Color is Red", Toast.LENGTH_SHORT).show();
                    can.setTextColor(Color.RED);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllViewActivity.this, TrashCanDetails.class);
                startActivity(i);
            }
        });
    }
}
