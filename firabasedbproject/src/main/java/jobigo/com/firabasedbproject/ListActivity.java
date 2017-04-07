package jobigo.com.firabasedbproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView lvFirebase;

    private DatabaseReference databaseReference;

    private List<MyWord> myList = new ArrayList<>();

    private ArrayAdapter<MyWord> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvFirebase = (ListView) findViewById(R.id.lvFirebase);

        arrayAdapter = new ArrayAdapter<MyWord>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, myList);
        lvFirebase.setAdapter(arrayAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MyWord myWord = new MyWord(ds.getKey(), ds.getValue().toString());
                    myList.add(myWord);
                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvFirebase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyWord wordToRemove = myList.get(i);
                databaseReference.child("words").child(wordToRemove.getId()).removeValue();
                myList.remove(wordToRemove);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        lvFirebase.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return true;
            }
        });
    }
}
