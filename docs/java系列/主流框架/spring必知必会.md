### 1、对象拷贝模式

网上有很多工具，支持浅拷贝或深拷贝的 Utils。举个例子，我们可以使用 org.springframework.beans.BeanUtils#copyProperties 对代码进行重构和优化：

```
@PostMapping
public User addUser(UserInputDTO userInputDTO){
    User user = new User();
    BeanUtils.copyProperties(userInputDTO,user);
    return userService.addUser(user);
}
```
BeanUtils.copyProperties 是一个浅拷贝方法，复制属性时，我们只需要把 DTO 对象和要转化的对象两个的属性值设置为一样的名称，并且保证一样的类型就可以了。如果你在做 DTO 转化的时候一直使用 set 进行属性赋值，那么请尝试这种方式简化代码，让代码更加清晰!


### jsr 303验证

hibernate 提供的 jsr 303 实现，我觉得目前仍然是很优秀的，具体如何使用，我不想讲，因为谷歌上你可以搜索出很多答案!

再以上班的 API 实例进行说明，我们现在对 DTO 数据进行检查：
```
public class UserDTO {
    @NotNull
    private String username;
    @NotNull
    private int age;
        //其他代码略
}
```
API 验证：
```
@PostMapping
    public UserDTO addUser(@Valid UserDTO userDTO){
            User user =  userDTO.convertToUser();
            User saveResultUser = userService.addUser(user);
            UserDTO result = userDTO.convertFor(saveResultUser);
            return result;
    }
```
我们需要将验证结果传给前端，这种异常应该转化为一个 api 异常(带有错误码的异常)。
```
@PostMapping
public UserDTO addUser(@Valid UserDTO userDTO, BindingResult bindingResult){
     checkDTOParams(bindingResult);

     User user =  userDTO.convertToUser();
     User saveResultUser = userService.addUser(user);
     UserDTO result = userDTO.convertFor(saveResultUser);
     return result;
}
private void checkDTOParams(BindingResult bindingResult){
     if(bindingResult.hasErrors()){
             //throw new 带验证码的验证错误异常
     }
}
```
BindingResult 是 Spring MVC 验证 DTO 后的一个结果集，可以参考spring 官方文档（http://spring.io/）。

检查参数后，可以抛出一个“带验证码的验证错误异常”。