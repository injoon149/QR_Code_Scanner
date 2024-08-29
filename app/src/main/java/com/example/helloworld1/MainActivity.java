package com.example.helloworld1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private TextView textViewDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextTask = findViewById(R.id.editTextTask);
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        textViewDeadline = findViewById(R.id.textViewDeadline);
        Button buttonAddTask = findViewById(R.id.buttonAddTask);
        Button buttonSelectDate = findViewById(R.id.buttonSelectDate);

        buttonSelectDate.setOnClickListener(v -> showDatePickerDialog());

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TaskAdapter adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);

        taskViewModel = new TaskViewModel(getApplication());
        taskViewModel.getAllTasks().observe(this, tasks -> {
            adapter.submitList(tasks);
        });

        buttonAddTask.setOnClickListener(v -> {
            String taskName = editTextTask.getText().toString();
            String taskCategory = spinnerCategory.getSelectedItem().toString();
            String deadline = textViewDeadline.getText().toString();
            if (!taskName.isEmpty() && !taskCategory.isEmpty() && !deadline.isEmpty()) {
                Task task = new Task(taskName, taskCategory, false, deadline);
                taskViewModel.insert(task);
                editTextTask.setText("");
                textViewDeadline.setText("");
            }
            else Toast.makeText(this, "Task or deadline cannot be empty!", Toast.LENGTH_SHORT).show();
        });

        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                // 할 일 항목 클릭 시 동작
            }

            @Override
            public void onDeleteClick(Task task) {
                taskViewModel.delete(task);
            }

            @Override
            public void onEditClick(Task task) {
                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                intent.putExtra("task_id", task.getId());
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            textViewDeadline.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }
}
