package hcl.poc.api.user;

import java.util.Objects;

public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String password;
    private boolean isAdmin;
    private String phone;
    private String address;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return isAdmin() == userDTO.isAdmin() && getId().equals(userDTO.getId()) && getEmail().equals(userDTO.getEmail()) && getName().equals(userDTO.getName()) && getPassword().equals(userDTO.getPassword()) && getPhone().equals(userDTO.getPhone()) && getAddress().equals(userDTO.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getName(), getPassword(), isAdmin(), getPhone(), getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String name, String password, boolean isAdmin, String phone, String address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.phone = phone;
        this.address = address;
    }
}
