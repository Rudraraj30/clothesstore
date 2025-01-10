-- -----------------------------------------------------
-- Schema full-stack-clothes-store
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `clothes-store`;
USE `clothes-store`;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS `Product`;
DROP TABLE IF EXISTS `Type`;
DROP TABLE IF EXISTS `Brand`;

-- Create the Brand table
CREATE TABLE `Brand` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL
);

-- Insert data into the Brand table
INSERT INTO `Brand` (`Name`) VALUES
    ('Levi\'s'),
    ('Nike'),
    ('Adidas'),
    ('Puma'),
    ('Zara'),
    ('H&M'),
    ('Gucci');

-- Create the Category table
CREATE TABLE `Type` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL
);

-- Insert data into the Category table
INSERT INTO `Type` (`Name`) VALUES
    ('T-Shirts'),
    ('Jeans'),
    ('Jackets'),
    ('Shoes'),
    ('Accessories');

-- Create the Product table
CREATE TABLE `Product` (
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Description` LONGTEXT,
    `Price` DECIMAL(10, 2) NOT NULL,
    `PictureUrl` VARCHAR(255),
    `ProductTypeId` INT NOT NULL,
    `ProductBrandId` INT NOT NULL,
    FOREIGN KEY (`ProductTypeId`) REFERENCES `Type`(`Id`) ON DELETE CASCADE,
    FOREIGN KEY (`ProductBrandId`) REFERENCES `Brand`(`Id`) ON DELETE CASCADE
);

-- Insert data into the Product table
INSERT INTO `Product` (`Name`, `Description`, `Price`, `PictureUrl`, `ProductTypeId`, `ProductBrandId`) VALUES
    ('Levi\'s 501 Jeans', 'Classic straight fit jeans with a comfortable waistband.', 59.99, 'https://example.com/images/levi501jeans.jpg', 2, 1),
    ('Nike Air Max 270', 'Sporty sneakers with maximum comfort and style.', 120.00, 'https://example.com/images/nikeairmax270.jpg', 4, 2),
    ('Adidas Originals Hoodie', 'A stylish hoodie with the Adidas logo on the front.', 49.99, 'https://example.com/images/adidashoodie.jpg', 3, 3),
    ('Puma Suede Classic', 'Iconic suede shoes with a modern twist.', 69.99, 'https://example.com/images/pumasuedeclassic.jpg', 4, 4),
    ('Zara Bomber Jacket', 'Fashionable bomber jacket with a sleek finish.', 89.99, 'https://example.com/images/zarabomberjacket.jpg', 3, 5),
    ('H&M Basic T-Shirt', 'A simple and versatile t-shirt perfect for everyday wear.', 14.99, 'https://example.com/images/hmbasictshirt.jpg', 1, 6),
    ('Gucci GG Belt', 'Luxury leather belt with the iconic GG logo.', 350.00, 'https://example.com/images/guccibbelt.jpg', 5, 7),
    ('Levi\'s 501 Jeans', 'Classic straight fit jeans with a comfortable waistband.', 59.99, 'https://example.com/images/levi501jeans.jpg', 2, 1);
