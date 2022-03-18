SET SQL_SAFE_UPDATES = 0;
DELETE FROM `users`;
insert  into `users`(`id`,`email`,`first_name`,`last_name`,`password`,`username`,`role`)values (2,'rupesh@gmail.com','Rupesh','Shrestha','$2y$10$Ksf4mVvSHraszPdDxhXhJuibNj6Q6v/ou01CH0IZMZl5CtaTmLnvC','rupesh','USER');
SET SQL_SAFE_UPDATES = 1;
