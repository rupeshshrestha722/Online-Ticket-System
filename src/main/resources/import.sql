SET SQL_SAFE_UPDATES = 0;
DELETE FROM `users`;
insert  into `users`(`id`,`email`,`first_name`,`last_name`,`password`,`username`,`role`)values (2,'rupesh@gmail.com','Rupesh','Shrestha','$2y$10$Ksf4mVvSHraszPdDxhXhJuibNj6Q6v/ou01CH0IZMZl5CtaTmLnvC','rupesh','USER');
DELETE FROM `bus_schedules`;
insert into `bus_schedules`(`id`,`bus_no`,`bus_model`,`source`,`destination`,`no_of_seats`,`fare`,`departureDate`,`arrivalDate`)values(1, '18182','A1','Kathmandu','Pokhara','50','200','2022-09-01','2022-09-01');
insert into `bus_schedules`(`id`,`bus_no`,`bus_model`,`source`,`destination`,`no_of_seats`,`fare`,`departureDate`,`arrivalDate`)values(2, '18183','A1','Kathmandu','Hetauda','50','100','2022-09-02','2022-09-02');
SET SQL_SAFE_UPDATES = 1;
