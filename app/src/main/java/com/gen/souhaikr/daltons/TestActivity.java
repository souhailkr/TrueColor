package com.gen.souhaikr.daltons;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gen.souhaikr.daltons.models.Question;

import java.util.List;
import com.gen.souhaikr.daltons.utils.dbHelper;


public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    int count = 1;
    String imageName;
    TextView ishiharaPlate;
    ImageView testPlate;
    LinearLayout quiz;
    TextView A;
    TextView B;
    TextView C;
    TextView D;
    TextView nothing;
    List<Question> quesList;
    int qid = 0;
    Question currentQ;
    int normal = 0;
    int protanopie = 0;
    int deutranopie = 0;
    int total = 0;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        dbHelper db = new dbHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);

        ishiharaPlate = findViewById(R.id.ishihara_plate);
        testPlate = findViewById(R.id.plate_image_view);
        quiz = findViewById(R.id.quiz);
        A = findViewById(R.id.A);
        A.setOnClickListener(this);

        B = findViewById(R.id.B);
        B.setOnClickListener(this);

        C = findViewById(R.id.C);
        C.setOnClickListener(this);

        D = findViewById(R.id.D);
        D.setOnClickListener(this);

        nothing = findViewById(R.id.nothing);
        nothing.setOnClickListener(this);


        testPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testPlate.setVisibility(View.GONE);
                ishiharaPlate.setVisibility(View.GONE);
                currentQ = quesList.get(qid);

                setQuestionView();

                quiz.setVisibility(View.VISIBLE);


            }

            private void setQuestionView() {
                A.setText(currentQ.getOPTA());
                B.setText(currentQ.getOPTB());
                C.setText(currentQ.getOPTC());
                D.setText(currentQ.getOPTD());

                qid++;
            }
        });

//

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.A:
                if (A.getText().equals(currentQ.getANSWER()))
                {
                    normal += 1 ;
                }
                else if (A.getText().equals(currentQ.getPr()))
            {
                protanopie += 1 ;
            }
                else if (A.getText().equals(currentQ.getDe()))
                {
                    deutranopie += 1 ;
                }

                Next();
                break;
            case R.id.B:
                if (B.getText().equals(currentQ.getANSWER()))
                {
                    normal += 1 ;
                }

                else if (B.getText().equals(currentQ.getPr()))
                {
                    protanopie += 1 ;
                }
                else if (B.getText().equals(currentQ.getDe()))
                {
                    deutranopie += 1 ;
                }
                Next();
                break;
            case R.id.C:
                if (C.getText().equals(currentQ.getANSWER()))
                {
                    normal += 1 ;
                }

                else if (C.getText().equals(currentQ.getPr()))
                {
                    protanopie += 1 ;
                }
                else if (C.getText().equals(currentQ.getDe()))
                {
                    deutranopie += 1 ;
                }
                Next();
                break;
            case R.id.D:
                if (D.getText().equals(currentQ.getANSWER()))
                {
                    normal += 1 ;
                }

                else if (D.getText().equals(currentQ.getPr()))
                {
                    protanopie += 1 ;
                }
                else if (D.getText().equals(currentQ.getDe()))
                {
                    deutranopie += 1 ;
                }
                Next();
                break;
            case R.id.nothing:

                    total += 1 ;

                Next();
                break;
        }

    }

    private void Next() {
        count = count + 1;

        if (count < 11) {
            imageName = "plate" + count;
            ishiharaPlate.setText("Ishihara Plate " + count +"/10");
            Log.i("a","aaaaa" + protanopie + deutranopie + normal + total);


            testPlate.setImageResource(getResources().getIdentifier(imageName, "drawable", getPackageName()));

            quiz.setVisibility(View.GONE);
            testPlate.setVisibility(View.VISIBLE);
            ishiharaPlate.setVisibility(View.VISIBLE);

        } else {
            if (normal>8)
            {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("normal.");
                    alertDialogBuilder.setPositiveButton("Go to Camera",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
                                    startActivity(intent);

                                }
                            });

            alertDialogBuilder.setNegativeButton("Restart Test",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        restart() ;
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


            }

            else if (protanopie>3)
            {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Protanopie..");
                alertDialogBuilder.setPositiveButton("Go to Camera",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                                startActivity(intent);

                            }
                        });

                alertDialogBuilder.setNegativeButton("Restart Test",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart() ;
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }

            if (deutranopie>4)
            {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Deutranopie..");
                alertDialogBuilder.setPositiveButton("Go to Camera",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                                startActivity(intent);

                            }
                        });

                alertDialogBuilder.setNegativeButton("Restart Test",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart() ;
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }

            if (total>7)
            {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Total blindness..");
                alertDialogBuilder.setPositiveButton("Go to Camera",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                                startActivity(intent);

                            }
                        });

                alertDialogBuilder.setNegativeButton("Restart Test",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart() ;
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }







        }
    }

    private void restart() {
        this.recreate();
    }
}
