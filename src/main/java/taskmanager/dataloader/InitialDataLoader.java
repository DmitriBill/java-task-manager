package taskmanager.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import taskmanager.model.Role;
import taskmanager.model.User;
import taskmanager.model.Task;
import taskmanager.service.RoleService;
import taskmanager.service.TaskService;
import taskmanager.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("${default.admin.mail}")
    private String defaultAdminMail;
    @Value("${default.admin.name}")
    private String defaultAdminName;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;
    @Value("${default.admin.image}")
    private String defaultSupervisorImage;

    @Autowired
    public InitialDataLoader(UserService userService, TaskService taskService, RoleService roleService) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);


        User admin = new User(
                defaultAdminMail,
                defaultAdminName,
                defaultAdminPassword,
                defaultSupervisorImage);
        userService.createUser(admin);
        userService.changeRoleToAdmin(admin);


        User manager = new User(
                "supervisor@mail.com",
                "Manager",
                "root123",
                "images/supervisor.jpg");
        userService.createUser(manager);
        userService.changeRoleToAdmin(manager);


        userService.createUser(new User(
                "john@mail.com",
                "John",
                "root123",
                "images/john.jpg"));


        userService.createUser(new User(
                "maria@mail.com",
                "Maria",
                "root123",
                "images/maria.jpg"));


        userService.createUser(new User(
                "andy@mail.com",
                "Andy",
                "root123",
                "images/andy.jpg"));


        userService.createUser(new User(
                "kelly@mail.com",
                "Kelly",
                "root123",
                "images/kelly.jpg"));


        userService.createUser(new User(
                "oskar@mail.com",
                "Oskar",
                "root123",
                "images/oskar.jpg"));

        userService.findAll().stream()
                .map(u -> "saved user: " + u.getName())
                .forEach(logger::info);


        //TASKS

        LocalDate today = LocalDate.now();


        taskService.createTask(new Task(
                "Work with developers to design algorithms and flowcharts ",
                "Work on the project structure.",
                today.minusDays(27),
                true,
                userService.getUserByEmail("john@mail.com").getName(),
                userService.getUserByEmail("john@mail.com")
        ));


        taskService.createTask(new Task(
                "Verify programs and systems ",
                "Projects Deployment.",
                today.minusDays(11),
                true,
                userService.getUserByEmail("maria@mail.com").getName(),
                userService.getUserByEmail("maria@mail.com")
        ));


        taskService.createTask(new Task(
                "Gather and evaluate user feedback",
                "Collect information.",
                today.minusDays(34),
                true,
                userService.getUserByEmail("andy@mail.com").getName(),
                userService.getUserByEmail("andy@mail.com")
        ));


        taskService.createTask(new Task(
                "Troubleshoot, debug and upgrade existing software",
                "Projects review.",
                today.minusDays(9),
                true,
                userService.getUserByEmail("kelly@mail.com").getName(),
                userService.getUserByEmail("kelly@mail.com")
        ));


        taskService.createTask(new Task(
                "Get quotation for hosting and domain",
                "Get quotation for hosting and domain, particularly if specialized hosting is involved such as VPS hosting, cloud hosting, or special hosting or environment requirements.",
                today.minusDays(2),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName(),
                userService.getUserByEmail("kelly@mail.com")
        ));


        taskService.createTask(new Task(
                "Recommend and execute improvements",
                "Monitor and analyze.",
                today.minusDays(7),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName(),
                userService.getUserByEmail("john@mail.com")
        ));


        taskService.createTask(new Task(
                "Check all web copywriting",
                "-",
                today,
                false,
                userService.getUserByEmail("supervisor@mail.com").getName(),
                userService.getUserByEmail("maria@mail.com")
        ));


        taskService.createTask(new Task(
                "Integrate software components and third-party programs",
                "Confirm that videos and audio files are in the correct places.",
                today.plusDays(2),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName(),
                userService.getUserByEmail("maria@mail.com")
        ));


        taskService.createTask(new Task(
                "Collaborating with management, departments and customers to identify end-user requirements and specifications",
                "Developers work with teams of coders to create software programs for computers, mobile devices and websites.",
                today.plusDays(5),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName(),
                userService.getUserByEmail("kelly@mail.com")
        ));


        taskService.createTask(new Task(
                "Producing clean, efficient code based on specifications",
                "Build efficient programs and systems that serve user needs.",
                today.plusDays(1),
                false,
                userService.getUserByEmail("maria@mail.com").getName(),
                userService.getUserByEmail("maria@mail.com")
        ));


        taskService.createTask(new Task(
                "Check the 404 page and redirects",
                "Try a non-existing address on your page to check the 404 page and 404 redirect pages are in place. Choose www vs no-www and make sure that ONLY one of them is working to ensure you don’t get penalized for duplicate content. After choosing one, make sure one redirects to the other.",
                today.plusDays(8),
                false,
                userService.getUserByEmail("andy@mail.com").getName(),
                userService.getUserByEmail("andy@mail.com")
        ));


        taskService.createTask(new Task(
                "Developing technical documentation to guide future software development projects",
                "They update end-user software and conduct quality control functions.",
                today.plusDays(13),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName(),
                userService.getUserByEmail("supervisor@mail.com")
        ));


        taskService.createTask(new Task(
                "Producing efficient and elegant code based on requirements",
                "-",
                today.plusDays(10),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName()
        ));


        taskService.createTask(new Task(
                "Create technical documentation for reference and reporting",
                "-",
                today.plusDays(18),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName()
        ));


        taskService.createTask(new Task(
                "Send the finished site to the client",
                "Send the finished site to the client and get feedback.",
                today.plusDays(16),
                false,
                userService.getUserByEmail("supervisor@mail.com").getName()
        ));

        taskService.findAll().stream().map(t -> "saved task: '" + t.getName()
                + "' for owner: " + getOwnerNameOrNoOwner(t)).forEach(logger::info);
    }

    private String getOwnerNameOrNoOwner(Task task) {
        return task.getOwner() == null ? "no owner" : task.getOwner().getName();
    }
}
