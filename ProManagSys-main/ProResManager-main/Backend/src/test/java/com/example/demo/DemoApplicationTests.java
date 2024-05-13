package com.example.demo;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskStatus;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.impl.TaskService;
import com.example.demo.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Date;


@SpringBootTest
@WebAppConfiguration
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private TaskService taskService;


    @BeforeEach
    public void setup() throws ParseException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse("07/07/2025");
        Task task = new Task();
        task.setTaskId(1L);
        when(taskService.createTask("hrban", "anlaki", date))
                .thenReturn(new Task());
        when(userService.registrerNewUser("epsi", "epsi@example.com", "Anas", "A", "password123", 1L))
                .thenReturn(Optional.of(new User()));
        
}

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        when(userService.getUserWithID(1L)).thenReturn(Optional.of(new User()));
        mockMvc.perform(get("/user/getbyid/{idUser}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByName() throws Exception {
        when(userService.getUserWithUserName("john")).thenReturn(Optional.of(new User()));
        mockMvc.perform(get("/user/getbyname/{userName}", "john"))
                .andExpect(status().isOk());
    }

    @Test
    void registerNewUser() throws Exception {
        when(userService.registrerNewUser("john", "john@example.com", "John", "Doe", "password123", 1L))
                .thenReturn(Optional.of(new User()));
        mockMvc.perform(post("/user/register")
                .param("userName", "john")
                .param("userEmail", "john@example.com")
                .param("userFirstName", "John")
                .param("userLastName", "Doe")
                .param("userPassword", "password123")
                .param("roleId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        when(userService.updateUser(1L, "john", "john@example.com", "John", "Doe", "password123", 1L))
                .thenReturn(1);
        mockMvc.perform(put("/user/update/{idUser}", 1L)
                .param("userName", "john")
                .param("userEmail", "john@example.com")
                .param("userFirstName", "John")
                .param("userLastName", "Doe")
                .param("userPassword", "password123")
                .param("roleId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void removeUser() throws Exception {
        when(userService.deleteUser(1L)).thenReturn(1);
        mockMvc.perform(delete("/user/remove/{idUser}", 1L))
                .andExpect(status().isOk());
    }
    @Test
    void createTask() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse("07/07/2025");
        when(taskService.createTask("task1", "description1", date))
                .thenReturn(new Task());
        
        mockMvc.perform(post("/task/create")
                .param("taskName", "task1")
                .param("description", "description1")
                .param("dueDate", "07/07/2025"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateTaskStatus() throws Exception {
        when(taskService.updateTaskStatus(1L, (TaskStatus.COMPLETED)))
                .thenReturn(1);
                mockMvc.perform(put("/task/updateStatus/{taskId}", 1L)
            .param("status", "COMPLETED"))
            .andExpect(status().isOk());
    }

    @Test
    void updateTask() throws Exception {
        when(taskService.updateTask(1L, "task1", "description1", new Date(), TaskStatus.COMPLETED));
        mockMvc.perform(put("/task/update/{taskId}", 1L)
                .param("taskName", "task1")
                .param("description", "description1")
                .param("dueDate", "2022-12-31")
                .param("status", "COMPLETED"))
                .andExpect(status().isOk());
    }

    @Test
    void getTaskById() throws Exception {
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(new Task()));
        mockMvc.perform(get("/task/get/{taskId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getTaskByName() throws Exception {
        when(taskService.getTaskByName("task1")).thenReturn(Optional.of(new Task()));
        mockMvc.perform(get("/task/get/{taskName}", "task1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);
        mockMvc.perform(delete("/task/remove/{taskId}", 1L))
                .andExpect(status().isOk());
    }
}