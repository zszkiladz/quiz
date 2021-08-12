package pl.szkiladz.quiz.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDto {

    @NotNull
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @NotNull
    @Size(min = 5)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public static UserDto from(User user) {
        final UserDto dto = new UserDto();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        return dto;
    }

    public User toEntity() {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
