drop database quanlykho;
create database quanlykho;
use quanlykho;

CREATE TABLE `role` (
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into role values ('ADMIN'), ('QUANLY'), ('NHANVIEN');

CREATE TABLE `account` (
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fullName` varchar(50) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `roleName` varchar(20) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_account_role` (`roleName`),
  CONSTRAINT `fk_account_role` FOREIGN KEY (`roleName`) REFERENCES `role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Account` (`fullName`, `userName`, `password`, `roleName`, `status`, `email`) VALUES
('Nguyễn Văn Thanh', 'admin', '1', 'ADMIN', 0, 'vanthanh@gmail.com');


CREATE TABLE `maytinh` (
  `maMay` varchar(50) NOT NULL,
  `tenMay` varchar(100) DEFAULT NULL,
  `soLuong` int(11) NOT NULL DEFAULT '0',
  `tenCpu` varchar(50) NOT NULL DEFAULT '0',
  `ram` varchar(50) NOT NULL DEFAULT '0',
  `cardManHInh` varchar(50) DEFAULT NULL,
  `giaNhap` double NOT NULL DEFAULT '0',
  `giaXuat` double NOT NULL DEFAULT '0',
  `mainBoard` varchar(50) DEFAULT NULL,
  `congSuatNguon` int(11) DEFAULT NULL,
  `loaiMay` varchar(50) DEFAULT NULL,
  `rom` varchar(50) DEFAULT NULL,
  `kichThuocMan` double DEFAULT NULL,
  `dungLuongPin` varchar(50) DEFAULT NULL,
  `xuatXu` varchar(50) DEFAULT NULL,
  `trangThai` bit(1) DEFAULT NULL,
  PRIMARY KEY (`maMay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `MayTinh` (`maMay`, `tenMay`, `tenCpu`, `ram`, `cardManHInh`, `giaNhap`,`giaXuat`, `mainBoard`, `congSuatNguon`, `loaiMay`, `rom`, `kichThuocMan`, `dungLuongPin`, `xuatXu`, `trangThai`) VALUES
('LP10', 'Laptop Lenovo IdeaPad Gaming 3', 'Intel Core i5 12500H', '16 GB', 'NVIDIA GeForce RTX 3050', 23490000,25000000, NULL, NULL, 'Laptop', '512 GB', 15.6, '4 Cell', 'Trung Quốc', 0),
('LP12', 'Laptop MSI Modern 14 B11MOU-1028VN',  'Intel Core i3 115G4', '8 GB', 'Intel UHD Graphics', 13090000,14990000, NULL, NULL, 'Laptop', '256 GB', 14, '3 Cell', 'Trung Quốc', 0),
('LP13', 'Laptop HP 15s-fq2663TU', 'Intel Core i3 1115G4', '4 GB', 'Intel UHD Graphics', 9990000,11000000, NULL, NULL, 'Laptop', '256 GB', 15.6, '3 Cell', 'Trung Quốc', 1),
('LP14', 'Laptop Lenovo IdeaPad 5 Pro 16IAH7',  'Intel Core i5 12500H', '16 GB', 'Intel Iris Xe Graphics', 22490000,24500000, NULL, NULL, 'Laptop', '512 GB', 16, '4 Cell', 'Trung Quốc', 0),
('LP15', 'Laptop Lenovo IdeaPad 5 Pro 16IAH7',  'Intel Core i7 12700H', '16 GB', 'Intel Iris Xe Graphics', 25190000,26900000, NULL, NULL, 'Laptop', '512 GB', 16, '75 Wh', 'Trung Quốc', 0),
('LP16', 'Laptop Acer Nitro Gaming AN515-57-54MV',  'Intel Core i5 11400H', '8', 'NVIDIA GeForce RTX 3050', 22990000,25000000, NULL, NULL, 'Laptop', '512 GB', 15.6, '4 Cell ', 'Trung Quốc', 0),
('LP17', 'Laptop MSI Gaming Katana GF66 12UCK-815VN',  'Intel Core i5 12450H', '8 GB', 'Intel UHD Graphics', 23190000,24900000, NULL, NULL, 'Laptop', '512 GB', 15.6, '53.5 Wh', 'Trung Quốc', 0),
('LP18', 'Laptop Asus TUF Gaming FX517ZC-HN077W',  'Intel Core i5 12450H', '8 GB', 'NVIDIA GeForce RTX 3050', 24990000,26990000, NULL, NULL, 'Laptop', '512 GB', 15.6, '4 Cell', 'Trung Quốc', 0),
('LP19', 'Laptop HP Gaming Victus 16-e0175AX',  'AMD Ryzen 5 5600H', '8 GB', 'NVIDIA GeForce RTX 3050 Ti', 19490000,22000000, NULL, NULL, 'Laptop', '512 GB', 16.1, '4 Cell', 'Trung Quốc', 0),
('LP20', 'Laptop MSI GF63 Thin 11UC-444VN',  'Intel Core i5 11400H', '8 GB', 'NVIDIA GeForce RTX 3050', 20790000,23200000, NULL, NULL, 'Laptop', '512 GB', 15.6, '3 Cell', 'Trung Quốc', 0),
('LP21', 'Laptop Asus TUF Gaming FX517ZE-HN045W','Intel Core i5 12450H', '8 GB', 'NVIDIA GeForce RTX 3050 Ti', 25990000,27900000, NULL, NULL, 'Laptop', '512 GB', 15.6, '4 Cell', 'Trung Quốc', 0),
('LP22', 'Laptop Lenovo Yoga Slim 7 Pro 14IHU5O', 'Intel Core i5 11300H', '16 GB', 'Intel Iris Xe Graphics', 23490000,25900000, NULL, NULL, 'Laptop', '512 GB', 14, '4 Cell ', 'Trung Quốc', 0),
('LP23', 'Laptop Gigabyte U4 UD-50VN823SO', 'Intel Core i5 1155G7', '16 GB', 'Intel Iris Xe Graphics', 15690000,17500000, NULL, NULL, 'Laptop', '512 GB', 14, '36 Wh', 'Trung Quốc', 0),
('LP24', 'Laptop Dell Vostro V5410 i5',  'Intel Core i5 11320H', '8 GB', 'Intel Iris Xe Graphics', 21490000,23000000, NULL, NULL, 'Laptop', '512 GB', 14, '4 Cell', 'Trung Quốc', 0),
('LP25', 'Laptop MSI Gaming GF63 Thin 11SC-666VN', 'Intel Core i5 11400H', '8 GB', 'NVIDIA GeForce GTX 1650', 18390000,20900000, NULL, NULL, 'Laptop', '512 GB', 15.6, '3 Cell', 'Trung Quốc', 1),
('LP3', 'Lenovo ThinkPad E14',  'Intel Core i5 11G352', '8GB', 'OnBoard', 15000000,17000000, NULL, NULL, 'Laptop', '521GB', 14, '45Wh', 'Trung Quốc',0),
('LP4', 'Lenovo Ideapad 3 15ITL6',  'Intel Core i3 1115G4', '8GB', 'Onboard', 10690000,12600000, NULL, NULL, 'Laptop', '512GB', 15.6, '35Wh', 'Trung Quốc', 0),
('LP5', 'Gigabyte Gaming G5 GD', 'Intel Core i5 11400H', '16GB', 'NVIDIA GeForce RTX 3050 4GB', 19290000,21000000, NULL, NULL, 'Laptop', '512GB', 15.6, '50Wh', 'Trung Quốc', 1),
('LP6', 'MSI Gaming GF63 Thin 11SC-1090VN', 'Intel Core i5 11400H', '8GB', 'NVIDIA GeForce GTX 1650 4GB', 17490000,19900000, NULL, NULL, 'Laptop', '512GB', 15.6, '50Wh', 'Trung Quốc', 1),
('LP7', 'Laptop Asus TUF Gaming FX506LHB-HN188W',  'Intel Core i5 10300H', '8 GB', 'NVIDIA GeForce GTX 1650', 17490000,20000000, NULL, NULL, 'Laptop', '512 GB', 15.6, '3 Cell', 'Trung Quốc', 0),
('LP8', 'Laptop MSI Gaming GF63 Thin 11SC-1090VN', 'Intel Core i5 11400H', '8 GB', 'NVIDIA GeForce GTX 1650 4GB', 18390000,20000000, NULL, NULL, 'Laptop', '512 GB', 15.6, '3 Cell', 'Trung Quốc', 0),
('LP9', 'Laptop Asus TUF Gaming FA506IHRB-HN019W', 'AMD Ryzen 5 4600H', '8 GB', 'NVIDIA GeForce GTX 1650', 16490000,18500000, NULL, NULL, 'Laptop', '512 GB', 12, '3 cell', 'Trung Quốc', 0),
('PC06', 'PC E-Power Office 08',  'Intel Core i5 11400', '16 GB', 'Intel UHD Graphics 730', 9690000, 11900000, 'Intel H510', 500, 'PC - Lắp ráp', '240 GB', NULL, NULL, 'Việt Nam', 0),
('PC1', 'PC E-Power Office 04',  'Intel Core i3 10105', '8GB', 'Intel HD Graphics 630', 7090000,9000000, 'Intel H510', 300, 'PC - Lắp ráp', '240GB', NULL, NULL, 'Việt Nam', 0),
('PC2', 'PC E-Power Office 05', 'Intel Core i5 10400', '8 GB', 'Intel UHD Graphics 630', 8290000,10000000, 'Intel H510', 600, 'PC - Lắp ráp', '8 GB', NULL, NULL, 'Việt Nam', 0),
('PC3', 'PC E-Power Office 07', 'Intel Core i5 11400', '8 GB', 'Intel UHD Graphics 730', 8990000,10500000, 'Intel H510', 550 , 'PC - Lắp ráp', '240 GB', NULL, NULL, 'Việt Nam', 0);


CREATE TABLE `nhacungcap` (
  `maNhaCungCap` varchar(50) NOT NULL,
  `tenNhaCungCap` varchar(50) DEFAULT NULL,
  `Sdt` varchar(50) DEFAULT NULL,
  `diaChi` varchar(150) DEFAULT NULL,
  `trangThai` bit(1) DEFAULT 0,
  PRIMARY KEY (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `NhaCungCap` (`maNhaCungCap`, `tenNhaCungCap`, `Sdt`, `diaChi`) VALUES
('ANPHAT', 'Công Ty TNHH Điều Khiển Tự Động An Phát', '02835109735', '86/21 Phan Tây Hồ, P. 7, Q. Phú Nhuận TP. Hồ Chí Minh'),
('CODO', 'Công Ty TNHH Thương Mại Dịch Vụ Hoàng Cố Đô', '02838115345', '622/16/5 Cộng Hòa, Phường 13, Quận Tân Bình, TP HCM		'),
('FPT', 'Công Ty Cổ Phần Bán Lẻ Kỹ Thuật Số FPT', '02873023456', '261 - 263 Khánh Hội, P2, Q4, TP. Hồ Chí Minh'),
('HACOM', 'Công ty Cổ phần đầu tư công nghệ HACOM', '1900 1903', 'Số 129 - 131, phố Lê Thanh Nghị, Phường Đồng Tâm, Quận Hai Bà Trưng, Hà Nội'),
('HOANGPHAT', 'Công Ty TNHH Thương Mại Hoàng Phát Hải Phòng', '02253250888', 'Số 4, Lô 2A Lê Hồng Phong, Ngô Quyền, Tp. Hải Phòng'),
('PHONGVU', 'Công ty cổ phần dịch vụ - thương mại Phong Vũ', '0967567567', 'Tầng 5, Số 117-119-121 Nguyễn Du, Phường Bến Thành, Quận 1, Thành Phố Hồ Chí Minh'),
('TGDĐ', 'Công ty cổ phần Thế Giới Di Động', '028 38125960', '128 Trần Quang Khải, P. Tân Định, Q.1, TP.Hồ Chí Minh'),
('VIETSTARS', 'Công ty CP Công nghệ Thương mại Dịch vụ Vietstars', '090 469 0212', ' Số 109 Lê Thanh Nghị  TP Hải dương');

CREATE TABLE `phieunhap` (
  `maPhieu` varchar(50) NOT NULL,
  `thoiGianTao` datetime DEFAULT NULL,
  `nguoiTao` varchar(50) DEFAULT NULL,
  `maNhaCungCap` varchar(50) NOT NULL,
  `tongTien` double NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_PhieuNhap_NhaCungCap` (`maNhaCungCap`),
  KEY `FK_PhieuNhap_Account` (`nguoiTao`),
  CONSTRAINT `FK_PhieuNhap_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `account` (`username`),
  CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`maNhaCungCap`) REFERENCES `nhacungcap` (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `phieuxuat` (
  `maPhieu` varchar(50) NOT NULL,
  `thoiGianTao` datetime DEFAULT NULL,
  `nguoiTao` varchar(50) NOT NULL,
  `tongTien` double NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_PhieuXuat_Account` (`nguoiTao`),
  CONSTRAINT `FK_PhieuXuat_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chitietphieunhap` (
  `maPhieu` varchar(50) NOT NULL,
  `maMay` varchar(50) NOT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`maPhieu`,`maMay`),
  KEY `FK_ChiTietPhieuNhap_MayTinh` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuNhap_MayTinh` FOREIGN KEY (`maMay`) REFERENCES `maytinh` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuNhap_PhieuNhap` FOREIGN KEY (`maPhieu`) REFERENCES `phieunhap` (`maPhieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chitietphieuxuat` (
  `maPhieu` varchar(50) NOT NULL,
  `maMay` varchar(50) NOT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`maPhieu`,`maMay`),
  KEY `FK_ChiTietPhieuXuat_MayTinh` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuXuat_MayTinh` FOREIGN KEY (`maMay`) REFERENCES `maytinh` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuXuat_PhieuXuat` FOREIGN KEY (`maPhieu`) REFERENCES `phieuxuat` (`maPhieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

