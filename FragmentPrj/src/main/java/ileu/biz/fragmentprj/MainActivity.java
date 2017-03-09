package ileu.biz.fragmentprj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    private Drawer drawer;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Drawer screen");

        createDrawer();

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.activity_main, new PostFragment())
//                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void createDrawer() {

        PrimaryDrawerItem item1 =
                new PrimaryDrawerItem()
                        .withIdentifier(1)
                        .withName("Post")
                        .withIcon(R.mipmap.ic_launcher);

        PrimaryDrawerItem item2 =
                new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Photos")
                .withIcon(R.mipmap.ic_launcher);

        PrimaryDrawerItem item3 =
                new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Todo")
                .withIcon(R.mipmap.ic_launcher);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1, item2, item3)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1) {
                            toolbar.setTitle("Post");
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentContainer, new PostFragment())
                                    .commit();
                        }
                        else if (drawerItem.getIdentifier() == 2) {
                            toolbar.setTitle("Photos");
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentContainer, new PhotosFragment())
                                    .commit();
                        }
                        else if (drawerItem.getIdentifier() == 3) {
                            toolbar.setTitle("Todo");
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentContainer, new TodoFragment())
                                    .commit();
                        }
                        return false;
                    }
                })
                .build();

        drawer.setSelection(item1);
    }
}
