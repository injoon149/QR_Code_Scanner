package com.example.helloworld1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

public class EditTaskActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private EditText editTextTask;
    private Spinner spinnerCategory;
    private TextView textViewDeadline;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTextTask = findViewById(R.id.editTextTask);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        textViewDeadline = findViewById(R.id.textViewDeadline);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonCancel = findViewById(R.id.buttonCancel);
        Button buttonSelectDate = findViewById(R.id.buttonSelectDate);

        // TaskViewModel 직접 생성
        taskViewModel = new TaskViewModel(getApplication());

        // Get the task ID from the intent
        taskId = getIntent().getIntExtra("task_id", -1);
        if (taskId != -1) {
            // Task를 직접 가져오기
            List<Task> tasks = taskViewModel.getAllTasks().getValue();
            if (tasks != null) {
                Task task = findTaskById(tasks, taskId);
                if (task != null) {
                    editTextTask.setText(task.getName());
                    // Set category and deadline
                    spinnerCategory.setSelection(getIndexForCategory(task.getCategory()));
                    textViewDeadline.setText(task.getDeadline());
                }
            }
        }

        // 날짜 선택 다이얼로그 표시
        buttonSelectDate.setOnClickListener(v -> showDatePickerDialog());

        buttonSave.setOnClickListener(v -> {
            String taskName = editTextTask.getText().toString();
            String taskCategory = spinnerCategory.getSelectedItem().toString();
            String deadline = textViewDeadline.getText().toString();

            // 필수 입력 항목이 비어 있는지 확인
            if (taskName.isEmpty() || taskCategory.isEmpty() || deadline.isEmpty()) {
                Toast.makeText(this, "Task or deadline cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            Task updatedTask = new Task(taskName, taskCategory, false, deadline);
            updatedTask.setId(taskId);
            taskViewModel.update(updatedTask);
            finish();
        });

        buttonCancel.setOnClickListener(v -> finish());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(EditTaskActivity.this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            textViewDeadline.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    private Task findTaskById(List<Task> tasks, int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    private int getIndexForCategory(String category) {
        // 스피너에서 카테고리의 인덱스를 찾는 로직 구현 (예시)
        for (int i = 0; i < spinnerCategory.getCount(); i++) {
            if (spinnerCategory.getItemAtPosition(i).toString().equalsIgnoreCase(category)) {
                return i;
            }
        }
        return 0; // 기본값으로 첫 번째 항목 선택
    }
}


