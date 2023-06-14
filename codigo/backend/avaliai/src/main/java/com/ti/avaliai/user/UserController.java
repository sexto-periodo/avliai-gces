package com.ti.avaliai.user;

//import com.ti.avaliai.user.dto.UserCreateRequestDTO;
import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse200;
import com.ti.avaliai.user.dto.UpdateUserRequestDTO;
import com.ti.avaliai.user.dto.UserDTO;
import com.ti.avaliai.user.dto.UserDeleteRequestDTO;
import com.ti.avaliai.user.dto.VerifyEmailRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Tag(name = "User - Endpoints de Usuário")
public class UserController extends BasicController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(method = "GET", summary = "Retorna os dados do usuário logado.", description = "Retorna os dados do usuário logado.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BaseSucessResponse<UserDTO>> getUser() {
        return ok(this.userService.getUserDTO());
    }

    @PutMapping
    @Operation(method = "PUT", summary = "Atualiza os dados do usuário logado.", description = "Atualiza os dados do usuário logado.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BaseSucessResponse<UserDTO>> updateUser(@RequestBody UpdateUserRequestDTO request) {
        return ok(this.userService.updateUser(request));
    }

    @GetMapping(value = "/all")
    @Operation(method = "GET", summary = "Lista todos os usuários cadastrados.", description = "Lista todos os usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }


    @Transactional
    @Operation(method = "DELETE", summary = "Deleta um usuário.", description = "Deleta um usuário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<BaseSucessResponse<NoPayloadSuccessResponse200>>  delete(@RequestBody UserDeleteRequestDTO userDeleteRequest) {
        this.userService.deleteUser(userDeleteRequest);
        return ok();
    }

    @PostMapping
    @Operation(method = "POST", summary = "Verifica se um e-mail de acadêmico é válido.", description = "Verifica se um e-mail de acadêmico é válido.")
    @ApiResponse(responseCode = "200", description = "OK")
    @RequestMapping(value = "/verify-email", method = RequestMethod.POST)
    public ResponseEntity<BaseSucessResponse<NoPayloadSuccessResponse200>> verifyEmail(@RequestBody VerifyEmailRequestDTO verifyEmailRequestDTO) {
            return ok(userService.verifyEmail(verifyEmailRequestDTO));
    }
}
