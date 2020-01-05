-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Jan 2020 pada 21.46
-- Versi server: 10.3.16-MariaDB
-- Versi PHP: 7.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sepatu`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `shoping`
--

CREATE TABLE `shoping` (
  `id_belanja` int(5) NOT NULL,
  `id` int(5) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total` varchar(20) NOT NULL,
  `pilihan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `shoping`
--

INSERT INTO `shoping` (`id_belanja`, `id`, `alamat`, `jumlah`, `total`, `pilihan`) VALUES
(1, 0, 'asdasdasdasd', 4, '0', ''),
(2, 0, 'asdasdasdasd', 4, '0', ''),
(3, 0, 'asdasdasdasdasd', 3, '0', ''),
(4, 0, 'asdasdasdasdasd', 3, '0', ''),
(5, 0, 'hallo bro wes isok', 5, '$10.00', 'Delivery'),
(6, 0, 'asdasdasdasdas', 6, '$12.00', 'Take In Store'),
(7, 0, 'asdasdasdasd', 5, '$10.00', 'Take In Store');

-- --------------------------------------------------------

--
-- Struktur dari tabel `teble_login`
--

CREATE TABLE `teble_login` (
  `id` int(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `teble_login`
--

INSERT INTO `teble_login` (`id`, `username`, `email`, `password`, `gender`) VALUES
(1, 'shoni', 'shoni@gmail.com', '1234', 'l\r\n'),
(2, 'bambang', 'asu@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `shoping`
--
ALTER TABLE `shoping`
  ADD PRIMARY KEY (`id_belanja`);

--
-- Indeks untuk tabel `teble_login`
--
ALTER TABLE `teble_login`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `shoping`
--
ALTER TABLE `shoping`
  MODIFY `id_belanja` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `teble_login`
--
ALTER TABLE `teble_login`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
