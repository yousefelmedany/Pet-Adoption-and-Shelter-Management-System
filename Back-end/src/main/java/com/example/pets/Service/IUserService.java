package com.example.pets.Service;
import com.example.pets.entity.*;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUserService {

    //user--section
    public User create_user(User user);
    public User Edit_user(int id,User user);
    boolean existsByUser_name(String user_name);
    boolean existsByEmail(String email);
    void deleteAllUsers();
    List<User> getAllUsers();
    void deleteUser(int id);



    //role--section
    public Role create_role(Role role);
    public Role delete_role(Long id);
    public boolean is_role_exist(String role);
    public List<Role> get_all_roles();
    public void add_role_to_user(String role,int id);
    public void remove_role_from_user(String role,int id);
    public Role find_role_by_name(String role);




    List<User> getUserByRole(String role);
    public User getUserByEmail(String email);
    public User changeRole(String Email,String role);
    public User changeDepartment(String Email,String department);
    public User add_image_touser(String Email,String image);


    List<User> getAllUsersByRole(String role);

    List<String> getAllUsersEmail();



    User changePassword(String email, String password);

    Image add_image(Image image);
    void delete_image(int id);
    Image get_image_by_name(String name);

    void sendVerificationEmail(User newUser, String siteURL ,String ConURL) throws MessagingException, UnsupportedEncodingException;
    void sendForgetPasswordEmail(User newUser) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String code);
}
