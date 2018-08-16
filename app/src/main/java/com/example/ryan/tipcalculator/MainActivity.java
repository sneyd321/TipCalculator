package com.example.ryan.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import javax.xml.validation.Validator;

public class MainActivity extends AppCompatActivity {

    //Field for input of amount
    EditText _edtAmount;
    ///Field for input of other tip
    EditText _edtOtherTip;
    //Field for input of hst checkbox
    CheckBox _chkHst;
    //Field for input of tip percentage spinner
    Spinner _spnTipPercent;
    //Field for input of number of people spinner
    Spinner _spnNumOfPeople;
    //Field for input of calculate button
    Button _btnCalculate;
    //Field for input of clear button
    Button _btnClear;
    //Field for output of tip amount
    TextView _txtTip;
    //Field for output of total amount
    TextView _txtTotal;
    //Field for output of tip per person amount
    TextView _txtPerPerson;

    Tip _tip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign the value of the id _edtAmount to _amount
        _edtAmount = (EditText) findViewById(R.id._edtAmount);
        //Assign the value of the id spnTipPercentage to _tipPercent
        _spnTipPercent = (Spinner) findViewById(R.id.spnTipPercentage);
        //Assign the value of the id spnNumOfPeople to _numPeople
        _spnNumOfPeople = (Spinner) findViewById(R.id.spnNumOfPeople);
        //Assign the value of the id edtOtherTip to _otherTip
        _edtOtherTip = (EditText) findViewById(R.id.edtOtherTip);
        //Assign the value of the id btnCalculate to _calculate
        _btnCalculate = (Button) findViewById(R.id.btnCalculate);
        //Assign the value of the id txtTip to _tip
        _txtTip = (TextView) findViewById(R.id.txtTip);
        //Assign the value of the id txtTotal to _total
        _txtTotal = (TextView) findViewById(R.id.txtTotal);
        //Assign the value of the id txtTipPerPerson to _perPerson
        _txtPerPerson = (TextView) findViewById(R.id.txtTipPerPerson);
        //Assign the value of the id btnClear to _clear
        _btnClear = (Button) findViewById(R.id.btnClear);
        //Assign the value of the id chkHst to _hst
        _chkHst = (CheckBox) findViewById(R.id.chkHst);


        //Create an on click listener for on calculate
        _btnCalculate.setOnClickListener(onCalculate);
        //Create an on click listener for on click
        _btnClear.setOnClickListener(onCLear);

        _spnNumOfPeople.setOnItemSelectedListener(onNumberOfPeople);

        _spnTipPercent.setOnItemSelectedListener(onTipPercent);


        _tip = new Tip();



    }
    private AdapterView.OnItemSelectedListener onTipPercent = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedValue = parent.getSelectedItem().toString();
            String[] percentage = new String[2];
            if (selectedValue.equals("Other")){
                _edtOtherTip.setVisibility(View.VISIBLE);
                percentage[0] = _edtOtherTip.getText().toString();
            }
            else {
                _edtOtherTip.setVisibility(View.INVISIBLE);
                _edtOtherTip.setText("");
                percentage = selectedValue.split("%");
            }
            if (!percentage[0].equals("")){
                _tip.setTipPercentage(validateInt(percentage[0]));

            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            _tip.setTipPercentage(10);
        }
    };

    private AdapterView.OnItemSelectedListener onNumberOfPeople = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            _tip.setNumberOfPeople(validateInt(parent.getSelectedItem().toString()));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            _tip.setNumberOfPeople(1);
        }
    };


    private View.OnClickListener onCalculate = new View.OnClickListener() {

       public void onClick(View v){
            _tip.setAmount(validateInt(_edtAmount.getText().toString()));

            if (_chkHst.isChecked()){
                _tip.setHst(true);
            }
            if (!_edtOtherTip.getText().toString().equals("")){
                _tip.setTipPercentage(validateInt(_edtOtherTip.getText().toString()));




            }

            if (_tip.getTipPercentage() > 0 && _tip.getAmount() > 0){
                _tip.calculateTip();
                _txtTip.setText("Tip amount: $" + formatPrice(_tip.getTipAmount()));
                _txtTotal.setText("Total amount: $" + formatPrice(_tip.getTotalAmount()));
                _txtPerPerson.setText("Amount Per Person: $" + formatPrice(_tip.getAmountPerPerson()));

                _txtTip.setVisibility(View.VISIBLE);
                _txtTotal.setVisibility(View.VISIBLE);
                _txtPerPerson.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(MainActivity.this, "Enter a Tip Value", Toast.LENGTH_SHORT).show();

            }





       }

    };

    private String formatPrice(double value){
        String formattedString = String.format("%.2f", value);
        return formattedString;

    }


    private View.OnClickListener onCLear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            _edtOtherTip.setText("");
            _edtAmount.setText("");
            if (_chkHst.isChecked()){
                _chkHst.toggle();
            }

            _tip = new Tip();
            _tip.setTipPercentage(10);
            _tip.setNumberOfPeople(1);

            _txtTip.setVisibility(View.INVISIBLE);
            _txtTotal.setVisibility(View.INVISIBLE);
            _txtPerPerson.setVisibility(View.INVISIBLE);

        }
    };

    private double validateInt(String amount){
        try{
            double value = Double.parseDouble(amount);
            return value;
        }
        catch (NumberFormatException ex){
            Toast.makeText(this, "Invalid Amount", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }



















}




