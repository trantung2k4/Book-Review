USE [master]
GO
/****** Object:  Database [RING]    Script Date: 11/3/2024 11:04:40 PM ******/
CREATE DATABASE [RING1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'RING', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\RING.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'RING_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\RING_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [RING] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [RING].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [RING1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [RING1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [RING1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [RING1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [RING1] SET ARITHABORT OFF 
GO
ALTER DATABASE [RING1] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [RING1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [RING1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [RING1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [RING1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [RING1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [RING1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [RING1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [RING1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [RING1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [RING1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [RING1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [RING1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [RING1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [RING1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [RING1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [RING1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [RING1] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [RING1] SET  MULTI_USER 
GO
ALTER DATABASE [RING1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [RING1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [RING1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [RING1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [RING1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [RING1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'RING', N'ON'
GO
ALTER DATABASE [RING] SET QUERY_STORE = OFF
GO
USE [RING]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userName] [varchar](30) NOT NULL,
	[pass] [varchar](50) NULL,
	[email] [varchar](1000) NULL,
	[isSeller] [int] NULL,
	[isAdmin] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC,
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Book]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[image] [varchar](500) NULL,
	[price] [float] NULL,
	[amount] [int] NULL,
	[title] [nvarchar](200) NULL,
	[description] [nvarchar](4000) NULL,
	[type] [nvarchar](200) NULL,
	[author] [nvarchar](200) NULL,
	[publisher] [int] NULL,
	[cateID] [int] NULL,
	[sellID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BookDetail]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BookDetail](
	[bookID] [int] NULL,
	[bWeight] [float] NULL,
	[size] [varchar](50) NULL,
	[pages] [int] NULL,
	[bDate] [smalldatetime] NULL,
	[bLanguage] [nvarchar](100) NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [pk] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](200) NULL,
	[image] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[orderID] [int] NULL,
	[bookID] [int] NULL,
	[amount] [int] NULL,
	[price] [float] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderReceipt]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderReceipt](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userID] [int] NULL,
	[username] [varchar](30) NULL,
	[fullName] [nvarchar](200) NULL,
	[email] [varchar](1000) NULL,
	[phone] [varchar](15) NULL,
	[oAddress] [nvarchar](1000) NULL,
	[oMessage] [nvarchar](1000) NULL,
	[oDate] [smalldatetime] NULL,
	[total] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Publisher]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Publisher](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[pubName] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Review](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userID] [int] NULL,
	[username] [varchar](30) NULL,
	[bookID] [int] NULL,
	[rContent] [nvarchar](1000) NULL,
	[rating] [int] NULL,
	[rDate] [smalldatetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Sub]    Script Date: 11/3/2024 11:04:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sub](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subName] [nvarchar](200) NULL,
	[cateId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [userName], [pass], [email], [isSeller], [isAdmin]) VALUES (1, N'treocaynho01629', N'123456', N'19130248@st.hcmuaf.edu.vn', 1, 1)
INSERT [dbo].[Account] ([id], [userName], [pass], [email], [isSeller], [isAdmin]) VALUES (2, N'trumhangrong01629', N'trumhangrong01629@gmail.com', N'1', 0, 0)
INSERT [dbo].[Account] ([id], [userName], [pass], [email], [isSeller], [isAdmin]) VALUES (3, N'chuotcon', N'321', N'mailkhongtontai@gmail.com', 1, 0)
INSERT [dbo].[Account] ([id], [userName], [pass], [email], [isSeller], [isAdmin]) VALUES (4, N'meomeomeo', N'123', N'blahblah@gmail.com', 1, 0)
INSERT [dbo].[Account] ([id], [userName], [pass], [email], [isSeller], [isAdmin]) VALUES (15, N'nguyenhuyphuoc9000', N'1234', N'trumhangrong01629@gmail.com', 0, 1)
INSERT [dbo].[Account] ([id], [userName], [pass], [email], [isSeller], [isAdmin]) VALUES (16, N'temp', N'123', N'none', 1, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Book] ON 

INSERT [dbo].[Book] ([id], [image], [price], [amount], [title], [description], [type], [author], [publisher], [cateID], [sellID]) VALUES (2, N'https://webdanhgia.vn/wp-content/uploads/2020/10/h%C6%B0%E1%BB%9Bng-d%E1%BA%ABn-s%E1%BB%AD-d%E1%BB%A5ng-mi-band-4.jpg', 0, 0, N'Những điều bạn cần biết về vòng đeo tay Mi Band 4', N'<p>V&ograve;ng đeo tay&nbsp;<strong>Mi Band&nbsp;</strong><strong>4</strong>&nbsp;l&agrave;&nbsp;chiếc v&ograve;ng thể thao th&ocirc;ng minh được&nbsp;rất nhiều người&nbsp;&ldquo;y&ecirc;u ngay từ c&aacute;i nh&igrave;n đầu ti&ecirc;n&rdquo;&nbsp;bởi kiểu d&aacute;ng thời trang,&nbsp;m&agrave;u sắc&nbsp;đa dạng&nbsp;v&agrave;&nbsp;chức năng&nbsp;vượt trội.&nbsp;Nếu bạn&nbsp;c&oacute; &yacute; định&nbsp;sử dụng&nbsp;Mi Band4 để kiểm tra sức khoẻ hoay tập luyện thể thao th&igrave; đ&oacute; l&agrave; sự lựa chọn tuyệt vời.&nbsp;<strong>Mi Band&nbsp;</strong>đưa ra những chức năng gi&uacute;p cuộc sống thường ng&agrave;y của bạn trở n&ecirc;n dễ d&agrave;ng với. Đừng bỏ qua&nbsp;<strong>Mi Band 4</strong>&nbsp;nếu bạn đang t&igrave;m một thiết bị th&ocirc;ng minh với mức gi&aacute; phải chăng. H&atilde;y c&ugrave;ng đọc th&ecirc;m về sản phẩm n&agrave;y nhe1</p>

<h2><strong>C&aacute;ch khởi động Mi Band 4</strong></h2>

<p>Nếu bạn nghĩ rằng v&ograve;ng đeo tay Mi Band 4 của m&igrave;nh sẽ kh&oacute; sử dụng th&igrave; c&oacute; lẽ bạn đ&atilde; lầm. Bạn chỉ cần bấm n&uacute;t khởi động v&agrave; c&aacute;c chức năng sẽ tự động được thiết lập. Nếu c&oacute; trục trặc trong qu&aacute; tr&igrave;nh sử dụng, bạn chỉ cần khởi động lại. C&aacute;ch l&agrave;m n&agrave;y c&oacute; thể sẽ tốt hơn việc kh&ocirc;i phục c&agrave;i đặt gốc.<br />
C&ocirc;ng ty TNHH DV &amp; TM vận tải &Aacute;nh Dương &ndash; Dịch vụ chuyển nh&agrave; gi&aacute; rẻ TOP đầu tại H&agrave; Nội. Được th&agrave;nh lập từ năm 2015, đến nay c&ocirc;ng ty đ&atilde; x&acirc;y dựng được thương hiệu của m&igrave;nh trong l&ograve;ng kh&aacute;ch h&agrave;ng với nhiệt huyết v&agrave; sự tận t&acirc;m của đội ngũ nh&acirc;n vi&ecirc;n gi&agrave;u kinh nghiệm, đặc biệt gi&aacute; th&agrave;nh rẻ l&agrave; điểm cộng lớn nhất khi kh&aacute;ch h&agrave;ng đ&aacute;nh gi&aacute; về c&aacute;c dịch vụ vận chuyển tại &Aacute;nh Dương.</p>

<p>&nbsp;</p>
', NULL, N'treocaynho01629', NULL, 1, 1)
INSERT [dbo].[Book] ([id], [image], [price], [amount], [title], [description], [type], [author], [publisher], [cateID], [sellID]) VALUES (4, N'https://webdanhgia.vn/wp-content/uploads/2022/06/Dich-vu-don-nha-tron-goi-o-Ha-Noi.jpg', 0, 0, N'Những điều bạn cần biết về vòng đeo tay Mi Band 4', N'<p>V&ograve;ng đeo tay&nbsp;<strong>Mi Band&nbsp;</strong><strong>4</strong>&nbsp;l&agrave;&nbsp;chiếc v&ograve;ng thể thao th&ocirc;ng minh được&nbsp;rất nhiều người&nbsp;&ldquo;y&ecirc;u ngay từ c&aacute;i nh&igrave;n đầu ti&ecirc;n&rdquo;&nbsp;bởi kiểu d&aacute;ng thời trang,&nbsp;m&agrave;u sắc&nbsp;đa dạng&nbsp;v&agrave;&nbsp;chức năng&nbsp;vượt trội.&nbsp;Nếu bạn&nbsp;c&oacute; &yacute; định&nbsp;sử dụng&nbsp;Mi Band4 để kiểm tra sức khoẻ hoay tập luyện thể thao th&igrave; đ&oacute; l&agrave; sự lựa chọn tuyệt vời.&nbsp;<strong>Mi Band&nbsp;</strong>đưa ra những chức năng gi&uacute;p cuộc sống thường ng&agrave;y của bạn trở n&ecirc;n dễ d&agrave;ng với. Đừng bỏ qua&nbsp;<strong>Mi Band 4</strong>&nbsp;nếu bạn đang t&igrave;m một thiết bị th&ocirc;ng minh với mức gi&aacute; phải chăng. H&atilde;y c&ugrave;ng đọc th&ecirc;m về sản phẩm n&agrave;y nhe1</p>

<h2>C&aacute;ch khởi động Mi Band 4</h2>

<p>Nếu bạn nghĩ rằng v&ograve;ng đeo tay Mi Band 4 của m&igrave;nh sẽ kh&oacute; sử dụng th&igrave; c&oacute; lẽ bạn đ&atilde; lầm. Bạn chỉ cần bấm n&uacute;t khởi động v&agrave; c&aacute;c chức năng sẽ tự động được thiết lập. Nếu c&oacute; trục trặc trong qu&aacute; tr&igrave;nh sử dụng, bạn chỉ cần khởi động lại. C&aacute;ch l&agrave;m n&agrave;y c&oacute; thể sẽ tốt hơn việc kh&ocirc;i phục c&agrave;i đặt gốc.</p>
', NULL, N'treocaynho01629', NULL, 1, 1)
INSERT [dbo].[Book] ([id], [image], [price], [amount], [title], [description], [type], [author], [publisher], [cateID], [sellID]) VALUES (5, N'https://webdanhgia.vn/wp-content/uploads/2022/06/Dich-vu-don-nha-tron-goi-o-Ha-Noi.jpg', 0, 0, N'Những điều bạn cần biết về vòng đeo tay Mi Band 4', N'<p>V&ograve;ng đeo tay&nbsp;<strong>Mi Band&nbsp;</strong><strong>4</strong>&nbsp;l&agrave;&nbsp;chiếc v&ograve;ng thể thao th&ocirc;ng minh được&nbsp;rất nhiều người&nbsp;&ldquo;y&ecirc;u ngay từ c&aacute;i nh&igrave;n đầu ti&ecirc;n&rdquo;&nbsp;bởi kiểu d&aacute;ng thời trang,&nbsp;m&agrave;u sắc&nbsp;đa dạng&nbsp;v&agrave;&nbsp;chức năng&nbsp;vượt trội.&nbsp;Nếu bạn&nbsp;c&oacute; &yacute; định&nbsp;sử dụng&nbsp;Mi Band4 để kiểm tra sức khoẻ hoay tập luyện thể thao th&igrave; đ&oacute; l&agrave; sự lựa chọn tuyệt vời.&nbsp;<strong>Mi Band&nbsp;</strong>đưa ra những chức năng gi&uacute;p cuộc sống thường ng&agrave;y của bạn trở n&ecirc;n dễ d&agrave;ng với. Đừng bỏ qua&nbsp;<strong>Mi Band 4</strong>&nbsp;nếu bạn đang t&igrave;m một thiết bị th&ocirc;ng minh với mức gi&aacute; phải chăng. H&atilde;y c&ugrave;ng đọc th&ecirc;m về sản phẩm n&agrave;y nhe1</p>

<h2>C&aacute;ch khởi động Mi Band 4</h2>

<p>Nếu bạn nghĩ rằng v&ograve;ng đeo tay Mi Band 4 của m&igrave;nh sẽ kh&oacute; sử dụng th&igrave; c&oacute; lẽ bạn đ&atilde; lầm. Bạn chỉ cần bấm n&uacute;t khởi động v&agrave; c&aacute;c chức năng sẽ tự động được thiết lập. Nếu c&oacute; trục trặc trong qu&aacute; tr&igrave;nh sử dụng, bạn chỉ cần khởi động lại. C&aacute;ch l&agrave;m n&agrave;y c&oacute; thể sẽ tốt hơn việc kh&ocirc;i phục c&agrave;i đặt gốc.</p>
', NULL, N'treocaynho01629', NULL, 2, 1)
INSERT [dbo].[Book] ([id], [image], [price], [amount], [title], [description], [type], [author], [publisher], [cateID], [sellID]) VALUES (6, N'https://webdanhgia.vn/wp-content/uploads/2022/06/Dich-vu-don-nha-tron-goi-o-Ha-Noi.jpg', 0, 0, N'Những điều bạn cần biết về vòng đeo tay Mi Band 4', N'<p>V&ograve;ng đeo tay&nbsp;<strong>Mi Band&nbsp;</strong><strong>4</strong>&nbsp;l&agrave;&nbsp;chiếc v&ograve;ng thể thao th&ocirc;ng minh được&nbsp;rất nhiều người&nbsp;&ldquo;y&ecirc;u ngay từ c&aacute;i nh&igrave;n đầu ti&ecirc;n&rdquo;&nbsp;bởi kiểu d&aacute;ng thời trang,&nbsp;m&agrave;u sắc&nbsp;đa dạng&nbsp;v&agrave;&nbsp;chức năng&nbsp;vượt trội.&nbsp;Nếu bạn&nbsp;c&oacute; &yacute; định&nbsp;sử dụng&nbsp;Mi Band4 để kiểm tra sức khoẻ hoay tập luyện thể thao th&igrave; đ&oacute; l&agrave; sự lựa chọn tuyệt vời.&nbsp;<strong>Mi Band&nbsp;</strong>đưa ra những chức năng gi&uacute;p cuộc sống thường ng&agrave;y của bạn trở n&ecirc;n dễ d&agrave;ng với. Đừng bỏ qua&nbsp;<strong>Mi Band 4</strong>&nbsp;nếu bạn đang t&igrave;m một thiết bị th&ocirc;ng minh với mức gi&aacute; phải chăng. H&atilde;y c&ugrave;ng đọc th&ecirc;m về sản phẩm n&agrave;y nhe1</p>

<h2>C&aacute;ch khởi động Mi Band 4</h2>

<p>Nếu bạn nghĩ rằng v&ograve;ng đeo tay Mi Band 4 của m&igrave;nh sẽ kh&oacute; sử dụng th&igrave; c&oacute; lẽ bạn đ&atilde; lầm. Bạn chỉ cần bấm n&uacute;t khởi động v&agrave; c&aacute;c chức năng sẽ tự động được thiết lập. Nếu c&oacute; trục trặc trong qu&aacute; tr&igrave;nh sử dụng, bạn chỉ cần khởi động lại. C&aacute;ch l&agrave;m n&agrave;y c&oacute; thể sẽ tốt hơn việc kh&ocirc;i phục c&agrave;i đặt gốc.</p>
', NULL, N'treocaynho01629', NULL, 2, 1)
INSERT [dbo].[Book] ([id], [image], [price], [amount], [title], [description], [type], [author], [publisher], [cateID], [sellID]) VALUES (7, N'https://webdanhgia.vn/wp-content/uploads/2022/06/Dich-vu-don-nha-tron-goi-o-Ha-Noi.jpg', 0, 0, N'Những điều bạn cần biết về vòng đeo tay Mi Band 4', N'<p>V&ograve;ng đeo tay&nbsp;<strong>Mi Band&nbsp;</strong><strong>4</strong>&nbsp;l&agrave;&nbsp;chiếc v&ograve;ng thể thao th&ocirc;ng minh được&nbsp;rất nhiều người&nbsp;&ldquo;y&ecirc;u ngay từ c&aacute;i nh&igrave;n đầu ti&ecirc;n&rdquo;&nbsp;bởi kiểu d&aacute;ng thời trang,&nbsp;m&agrave;u sắc&nbsp;đa dạng&nbsp;v&agrave;&nbsp;chức năng&nbsp;vượt trội.&nbsp;Nếu bạn&nbsp;c&oacute; &yacute; định&nbsp;sử dụng&nbsp;Mi Band4 để kiểm tra sức khoẻ hoay tập luyện thể thao th&igrave; đ&oacute; l&agrave; sự lựa chọn tuyệt vời.&nbsp;<strong>Mi Band&nbsp;</strong>đưa ra những chức năng gi&uacute;p cuộc sống thường ng&agrave;y của bạn trở n&ecirc;n dễ d&agrave;ng với. Đừng bỏ qua&nbsp;<strong>Mi Band 4</strong>&nbsp;nếu bạn đang t&igrave;m một thiết bị th&ocirc;ng minh với mức gi&aacute; phải chăng. H&atilde;y c&ugrave;ng đọc th&ecirc;m về sản phẩm n&agrave;y nhe1</p>

<h2>C&aacute;ch khởi động Mi Band 4</h2>

<p>Nếu bạn nghĩ rằng v&ograve;ng đeo tay Mi Band 4 của m&igrave;nh sẽ kh&oacute; sử dụng th&igrave; c&oacute; lẽ bạn đ&atilde; lầm. Bạn chỉ cần bấm n&uacute;t khởi động v&agrave; c&aacute;c chức năng sẽ tự động được thiết lập. Nếu c&oacute; trục trặc trong qu&aacute; tr&igrave;nh sử dụng, bạn chỉ cần khởi động lại. C&aacute;ch l&agrave;m n&agrave;y c&oacute; thể sẽ tốt hơn việc kh&ocirc;i phục c&agrave;i đặt gốc.</p>
', NULL, N'treocaynho01629', NULL, 1, 1)
INSERT [dbo].[Book] ([id], [image], [price], [amount], [title], [description], [type], [author], [publisher], [cateID], [sellID]) VALUES (8, N'https://webdanhgia.vn/wp-content/uploads/2022/06/Dich-vu-don-nha-tron-goi-o-Ha-Noi.jpg', 0, 0, N'50 Dịch vụ chuyển nhà trọn gói giá rẻ Hà Nội 2022, từ 380k/chuyến', N'<p>Webdanhgia.vn chia sẽ tới bạn đọc &ldquo;<a href="https://webdanhgia.vn/30-dich-vu-chuyen-nha-don-nha-tron-goi-gia-re-nhat-ha-noi-2022/">50 dịch vụ chuyển nh&agrave; trọn g&oacute;i gi&aacute; rẻ nhất H&agrave; N&ocirc;i năm 2022</a>&ldquo;. Nếu bạn đang quan t&acirc;m v&agrave; t&igrave;m kiếm th&ocirc;ng tin về c&aacute;c dịch vụ chuyển nh&agrave;&nbsp;<strong>CHUYẾN NGHIỆP, UY T&Iacute;N, GI&Aacute; RẺ</strong>&nbsp;tại H&agrave; Nội th&igrave; h&atilde;y d&agrave;nh khoảng 3-5 ph&uacute;t để đọc b&agrave;i viết của Webdanhgia, ch&uacute;ng t&ocirc;i tin rằng b&agrave;i viết n&agrave;y sẽ gi&uacute;p &iacute;ch cho bạn n&agrave;o đang c&oacute; &yacute; định thu&ecirc; dịch vụ chuyển nh&agrave; m&agrave; loay hoay chưa biết g&igrave; về gi&aacute; cả cũng như quy tr&igrave;nh, c&aacute;c bước, c&aacute;ch thức chuyển nh&agrave;. H&atilde;y đọc b&agrave;i viết của ch&uacute;ng t&ocirc;i bạn sẽ c&oacute; c&aacute;i nh&igrave;n tổng quan về tất tần tất c&aacute;c th&ocirc;ng tin của từng c&ocirc;ng ty cung cấp dịch vụ chuyển nh&agrave; đang c&oacute; chất lượng h&agrave;ng đầu tại H&agrave; Nội.</p>

<p>Review dịch vụ chuyển nh&agrave; &ndash; &ldquo;review dich vu chuyen nha&rdquo; tại H&agrave; Nội th&igrave; c&aacute;c c&ocirc;ng ty nằm trong danh s&aacute;ch b&agrave;i viết được ch&uacute;ng t&ocirc;i t&igrave;m hiểu kỹ c&agrave;ng, chắt lọc, chọn lọc v&agrave; x&aacute;c thực th&ocirc;ng tin trước khi đăng l&ecirc;n, vị tr&iacute;, thứ hạng ho&agrave;n to&agrave;n dựa v&agrave;o quy m&ocirc;, chất lượng dịch vụ, mức độ uy t&iacute;n, sự chuy&ecirc;n nghiệp, th&acirc;m ni&ecirc;n hoạt động&hellip;. v&agrave; đặc biệt Webdanhgia thu thập rất nhiều những feedback, những nhận x&eacute;t, những b&agrave;i đ&aacute;nh gi&aacute; hay cả những b&agrave;i &ldquo;bốc phốt dịch vụ chuyển nh&agrave;&rdquo; &hellip;.n&ecirc;n bạn đọc ho&agrave;n to&agrave;n y&ecirc;n t&acirc;m về chất lượng v&agrave; t&iacute;nh x&aacute;c thực của từng c&ocirc;ng ty. H&atilde;y thật s&aacute;ng suốt chọn cho m&igrave;nh dịch vụ chuyển nh&agrave; gi&aacute; rẻ giữa h&agrave;ng trăm đơn vị cung cấp dịch vụ tại H&agrave; Nội, c&ugrave;ng Webdanhgia t&igrave;m hiểu nh&eacute;.</p>

<p>Bạn cần một dịch vụ chuyển nh&agrave; trọn g&oacute;i ở H&agrave; Nội chuy&ecirc;n nghiệp, uy t&iacute;n để chuyển đồ của m&igrave;nh đến địa chỉ mới? Bạn hiện đang c&oacute; rất nhiều đồ d&ugrave;ng trong nh&agrave;, t&agrave;i liệu, thiết bị văn ph&ograve;ng cần được vận chuyển cẩn thận nhưng kh&ocirc;ng biết đ&acirc;u l&agrave; dịch vụ chuyển nh&agrave; TỐT ở H&agrave; Nội? Giữa h&agrave;ng trăm c&ocirc;ng ty, to&agrave;n l&agrave; những lời&nbsp;<strong>QUẢNG C&Aacute;O C&Oacute; C&Aacute;NH</strong>&nbsp;l&agrave;m bạn hoang mang, Vậy để gi&uacute;p kh&aacute;ch h&agrave;ng lựa chọn được dịch vụ tốt nhất, h&ocirc;m nay Webdanhgia.vn sẽ giới thiệu đến bạn 50 c&ocirc;ng ty chuyển nh&agrave; trọn g&oacute;i gi&aacute; rẻ tại H&agrave; Nội trong năm 2022<img alt="" src="https://webdanhgia.vn/wp-content/uploads/2022/06/Dich-vu-don-nha-tron-goi-o-Ha-Noi.jpg" style="height:600px; width:900px" /></p>
', NULL, N'treocaynho01629', NULL, 1, 1)
SET IDENTITY_INSERT [dbo].[Book] OFF
GO
SET IDENTITY_INSERT [dbo].[BookDetail] ON 

INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (1, NULL, NULL, NULL, CAST(N'2024-11-03T15:59:00' AS SmallDateTime), NULL, 16)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (2, 0, N'', 0, CAST(N'2024-11-03T16:15:00' AS SmallDateTime), N'', 17)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (3, NULL, NULL, NULL, CAST(N'2024-11-03T16:00:00' AS SmallDateTime), NULL, 18)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (4, NULL, NULL, NULL, CAST(N'2024-11-03T16:00:00' AS SmallDateTime), NULL, 19)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (5, NULL, NULL, NULL, CAST(N'2024-11-03T16:10:00' AS SmallDateTime), NULL, 20)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (6, NULL, NULL, NULL, CAST(N'2024-11-03T16:10:00' AS SmallDateTime), NULL, 21)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (7, NULL, NULL, NULL, CAST(N'2024-11-03T16:11:00' AS SmallDateTime), NULL, 22)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (8, NULL, NULL, NULL, CAST(N'2024-11-03T16:14:00' AS SmallDateTime), NULL, 23)
INSERT [dbo].[BookDetail] ([bookID], [bWeight], [size], [pages], [bDate], [bLanguage], [id]) VALUES (9, NULL, NULL, NULL, CAST(N'2024-11-03T16:16:00' AS SmallDateTime), NULL, 24)
SET IDENTITY_INSERT [dbo].[BookDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (2, N'Sách giáo khoa', N'https://webdanhgia.vn/wp-content/uploads/2022/07/Dich-vu-ve-sinh-cong-nghiep-gia-re.jpg')
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (3, N'Tiểu thuyết', NULL)
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (4, N'Ký sự', NULL)
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (5, N'Thơ ca', NULL)
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (6, N'Sách ảnh', NULL)
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (7, N'Cẩm nang', NULL)
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (8, N'asd1', N'a')
INSERT [dbo].[Category] ([id], [categoryName], [image]) VALUES (10, N'asd12', N'asd')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Publisher] ON 

INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (1, N'NXB Trẻ')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (2, N'NXB Kim Đồng')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (3, N'NXB Tổng Hợp TP.HCM')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (4, N'Bách Việt')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (5, N'Nhã Nam')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (6, N'Skybooks')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (7, N'NXB Giáo Dục')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (8, N'NXB Giáo Dục Việt Nam')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (9, N'Megabook')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (10, N'NXB Phụ nữ')
INSERT [dbo].[Publisher] ([id], [pubName]) VALUES (11, N'NXB Tân Việt')
SET IDENTITY_INSERT [dbo].[Publisher] OFF
GO
SET IDENTITY_INSERT [dbo].[Review] ON 

INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (1, 1, N'treocaynho01629', 4, N'Hay', 5, CAST(N'2024-11-03T16:07:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (2, 1, N'treocaynho01629', 4, N'Good!', 3, CAST(N'2024-11-03T16:11:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (3, 1, N'treocaynho01629', 4, N'Hay', 5, CAST(N'2024-11-03T16:11:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (4, 1, N'treocaynho01629', 4, N'hay', 5, CAST(N'2024-11-03T16:11:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (5, 1, N'treocaynho01629', 4, N'hay', 5, CAST(N'2024-11-03T16:11:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (6, 1, N'treocaynho01629', 4, N'hay', 5, CAST(N'2024-11-03T16:11:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (7, 1, N'treocaynho01629', 4, N'hay', 5, CAST(N'2024-11-03T16:11:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (8, 1, N'treocaynho01629', 8, N'Good', 5, CAST(N'2024-11-03T16:14:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (9, 1, N'treocaynho01629', 9, N'Hay!', 5, CAST(N'2024-11-03T16:16:00' AS SmallDateTime))
INSERT [dbo].[Review] ([id], [userID], [username], [bookID], [rContent], [rating], [rDate]) VALUES (10, 3, N'chuotcon', 2, N'aasd', 5, CAST(N'2024-11-03T18:42:00' AS SmallDateTime))
SET IDENTITY_INSERT [dbo].[Review] OFF
GO
ALTER TABLE [dbo].[Book]  WITH NOCHECK ADD  CONSTRAINT [fk_cID2] FOREIGN KEY([cateID])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Book] NOCHECK CONSTRAINT [fk_cID2]
GO
ALTER TABLE [dbo].[Book]  WITH NOCHECK ADD  CONSTRAINT [fk_pId] FOREIGN KEY([publisher])
REFERENCES [dbo].[Publisher] ([id])
GO
ALTER TABLE [dbo].[Book] NOCHECK CONSTRAINT [fk_pId]
GO
ALTER TABLE [dbo].[OrderReceipt]  WITH CHECK ADD  CONSTRAINT [fk_uId2] FOREIGN KEY([userID], [username])
REFERENCES [dbo].[Account] ([id], [userName])
GO
ALTER TABLE [dbo].[OrderReceipt] CHECK CONSTRAINT [fk_uId2]
GO
ALTER TABLE [dbo].[Review]  WITH NOCHECK ADD  CONSTRAINT [fk_uId] FOREIGN KEY([userID], [username])
REFERENCES [dbo].[Account] ([id], [userName])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [fk_uId]
GO
ALTER TABLE [dbo].[Sub]  WITH CHECK ADD  CONSTRAINT [fk_cId] FOREIGN KEY([cateId])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Sub] CHECK CONSTRAINT [fk_cId]
GO
USE [master]
GO
ALTER DATABASE [RING] SET  READ_WRITE 
GO
