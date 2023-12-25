package com.example.pets.Service;

import com.example.pets.repository.*;
import com.example.pets.security.AppUserDetails;
import com.example.pets.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class UserService implements IUserService, UserDetailsService {


    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    ImageRepository imageRepositry;
    @Qualifier("email1Sender")
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private final PasswordEncoder passwordEncoder;


    //-----------------------------------------------UserMethods--------------------------------------------------//
    @Override
    public User create_user(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public boolean existsByUser_name(String user_name) {
        return userRepo.existsByUsername(user_name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            user = userRepo.findByEmail(username);
            if (user.isEmpty())
                throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
        }
        return new AppUserDetails(user.get());
    }
    @Override
    public User Edit_user(int id, User user) {
        User old_user = userRepo.findById(id).orElse(null);
        assert old_user != null;
        old_user.setCompany_name(user.getCompany_name());
        old_user.setUsername(user.getUsername());
        old_user.setPassword(user.getPassword());
        old_user.setEmail(user.getEmail());
        old_user.setPhone_number(user.getPhone_number());
        old_user.setCountry(user.getCountry());
        old_user.setAddress(user.getAddress());
        old_user.setImg(user.getImg());
        old_user.setRoles(user.getRoles());
        old_user.setEnabled(user.isEnabled());
        old_user.setDepartment(user.getDepartment());
        old_user.setAccountNonExpired(user.isAccountNonExpired());
        old_user.setAccountNonLocked(user.isAccountNonLocked());
        old_user.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userRepo.save(old_user);
        return old_user;
    }

    public User changePassword(String email, String password) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            return null; // Handle the case when the user is not found
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return user;
    }

    @Override
    public Image add_image(Image image) {
        return imageRepositry.save(image);
    }


    @Override
    public void delete_image(int id) {
        imageRepositry.deleteById(id);
    }

    @Override
    public Image get_image_by_name(String name) {
        return imageRepositry.findByName(name).orElse(null);
    }


    @Override
    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(int id) {
//        this.changeRole(Objects.requireNonNull(userRepo.findById(id).orElse(null)).getEmail(), "USER");
        User user = userRepo.findById(id).orElse(null);
        if(user==null){
            return;
        }
        Role role = roleRepository.findByName("USER").orElseGet(() -> roleRepository.save(new Role("USER")));
        Set<Role> roles = Collections.singleton(role);
        user.setRoles(roles);
        userRoleRepository.updateUserRoleInUsersRolesTable(user.getId(), Math.toIntExact(role.getId()));
    }

    @Override
    public List<User> getUserByRole(String role) {
        Set<Role> roles = Set.of(Objects.requireNonNull(roleRepository.findByName(role).orElse(null)));
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User changeRole(String email, String roleName) {
        try {
            User user = userRepo.findByEmail(email).orElse(null);
            if (user == null) {
                return null; // Handle the case when the user is not found
            }
            Role role = roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(new Role(roleName)));
            Set<Role> roles = Collections.singleton(role);
            user.setRoles(roles);
            userRoleRepository.updateUserRoleInUsersRolesTable(user.getId(), Math.toIntExact(role.getId()));
            // Save the user entity
//            userRepo.save(user);
            return user;
        } catch (Exception e) {
            // Handle the exception gracefully by logging and potentially re-throwing it
            System.err.println("Error while changing user role: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error while changing user role", e);
        }

    }

    @Override
    public User changeDepartment(String email, String department) {
        try {
            User user = userRepo.findByEmail(email).orElse(null);
            if (user == null) {
                return null; // Handle the case when the user is not found
            }
            user.setDepartment(department);
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            // Handle the exception gracefully by logging and potentially re-throwing it
            System.err.println("Error while changing user department: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error while changing user department", e);
        }
    }

    @Override
    public User add_image_touser(String Email, String image) {
        try {
            User user = userRepo.findByEmail(Email).orElse(null);
            if (user == null) {
                return null; // Handle the case when the user is not found
            }
            user.setImg(image);
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            // Handle the exception gracefully by logging and potentially re-throwing it
            System.err.println("Error while changing user image: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error while changing user image", e);
        }
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        Set<Role> roles = Set.of(Objects.requireNonNull(roleRepository.findByName(role).orElse(null)));
        return new ArrayList<>(userRepo.findAllByRoles(roles));
    }

    @Override
    public List<String> getAllUsersEmail() {
        List<String> emails = new ArrayList<>();
        List<User> users = userRepo.findAll();
        for (User user : users) {
            emails.add(user.getEmail());
        }
        return emails;
    }

    //---------------------------------------------------RoleMethods--------------------------------------------------//
    @Override
    public Role create_role(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role find_role_by_name(String role) {
        if (roleRepository.findByName(role).isEmpty())
            return null;
        else
            return roleRepository.findByName(role).get();
    }

    @Override
    public boolean is_role_exist(String role) {
        return false;
    }

    public Optional<Role> get_role_by_id(Long id) {
        return roleRepository.findById(id);
    }

    public Role Edit_role(Long id, Role role) {
        Role old_role = roleRepository.findById(id).orElse(null);
        assert old_role != null;
        old_role.setName(role.getName());
        roleRepository.save(old_role);
        return old_role;
    }

    public List<Role> get_all_roles() {
        return roleRepository.findAll();
    }

    @Override
    public void add_role_to_user(String role, int id) {

    }

    @Override
    public void remove_role_from_user(String role, int id) {

    }

    public Role delete_role(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        roleRepository.deleteById(id);
        return role;
    }


    //mail verification

    @Override
    public void sendVerificationEmail(User user, String siteURL, String ConURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "modysamir@gamil.com";
        String senderName = "Galaxy team";
        String subject = "Account Confirmation Code";
        String content = "Dear [[name]],<br>"
                + "Thank you for signing up with Galaxy!<br>"
                + " To complete your registration and ensure the security of your account, please click on the following verification link:<br>"
                + "<h3><a href=\"[[URL]]\" onclick=\"return handleLinkClick(event);\">VERIFY</a></h3>"
                + "<br>If you didn't request this confirmation code, please ignore this email. "
                + "<br>Your account will remain inactive until you confirm your email address."
                + "If you encounter any issues or have questions, please don't hesitate to contact our support team through this link "
                + "<h5><a href=\"[[ConURL]]\" onclick=\"return handleLinkClick(event);\">Contact Us</a></h5>"
                + "Thank you for choosing Galaxy!<br>"
                + "Galaxy team";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);
        content = content.replace("[[ConURL]]", ConURL);

        // JavaScript event handler
        String script = "<script type=\"text/javascript\">\n"
                + "    function handleLinkClick(event) {\n"
                + "        event.preventDefault(); // Prevent the link from opening in a new tab\n"
                + "        window.location.href = event.target.getAttribute(\"href\"); // Navigate to the link's URL\n"
                + "        return false;\n"
                + "    }\n"
                + "</script>\n";

        // Append the JavaScript to the email content
        content = script + content;

        helper.setText(content, true);

        mailSender.send(message);
        System.out.println("Email has been sent");
    }


    @Override
    public void sendForgetPasswordEmail(User user) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "modysamir@gamil.com";
        String senderName = "Galaxy team";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Here is the OTP code to forget password :<br>"
                + "<h3>[[URL]]</h3>"
                + "Thank you,<br>"
                + "Galaxy team";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
        System.out.println("Email has been sent");

    }


    public boolean verify(String verificationCode) {
        User user = userRepo.findByVerificationCode(verificationCode).orElse(null);
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepo.save(user);
            return true;
        }

    }


}





