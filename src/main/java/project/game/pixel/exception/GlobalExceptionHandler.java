//package project.game.pixel.exception;
//
//public class GlobalExceptionHandler {
//}
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public ResponseEntity<String> handleUserExists(UserAlreadyExistsException e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//    }
//
//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//    }
//
//    // 기타 예외 처리...
//}
