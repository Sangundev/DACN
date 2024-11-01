USE [master]
GO
/****** Object:  Database [Food_Web]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE DATABASE [Food_Web]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Food_Web', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Food_Web.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Food_Web_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Food_Web_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Food_Web] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Food_Web].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Food_Web] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Food_Web] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Food_Web] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Food_Web] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Food_Web] SET ARITHABORT OFF 
GO
ALTER DATABASE [Food_Web] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Food_Web] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Food_Web] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Food_Web] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Food_Web] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Food_Web] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Food_Web] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Food_Web] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Food_Web] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Food_Web] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Food_Web] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Food_Web] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Food_Web] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Food_Web] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Food_Web] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Food_Web] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Food_Web] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Food_Web] SET RECOVERY FULL 
GO
ALTER DATABASE [Food_Web] SET  MULTI_USER 
GO
ALTER DATABASE [Food_Web] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Food_Web] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Food_Web] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Food_Web] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Food_Web] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Food_Web] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Food_Web', N'ON'
GO
ALTER DATABASE [Food_Web] SET QUERY_STORE = OFF
GO
USE [Food_Web]
GO
/****** Object:  Table [dbo].[__MigrationHistory]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[__MigrationHistory](
	[MigrationId] [nvarchar](150) NOT NULL,
	[ContextKey] [nvarchar](300) NOT NULL,
	[Model] [varbinary](max) NOT NULL,
	[ProductVersion] [nvarchar](32) NOT NULL,
 CONSTRAINT [PK_dbo.__MigrationHistory] PRIMARY KEY CLUSTERED 
(
	[MigrationId] ASC,
	[ContextKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AspNetRoles]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AspNetRoles](
	[Id] [nvarchar](128) NOT NULL,
	[Name] [nvarchar](256) NOT NULL,
 CONSTRAINT [PK_dbo.AspNetRoles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AspNetUserClaims]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AspNetUserClaims](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [nvarchar](128) NOT NULL,
	[ClaimType] [nvarchar](max) NULL,
	[ClaimValue] [nvarchar](max) NULL,
 CONSTRAINT [PK_dbo.AspNetUserClaims] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AspNetUserLogins]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AspNetUserLogins](
	[LoginProvider] [nvarchar](128) NOT NULL,
	[ProviderKey] [nvarchar](128) NOT NULL,
	[UserId] [nvarchar](128) NOT NULL,
 CONSTRAINT [PK_dbo.AspNetUserLogins] PRIMARY KEY CLUSTERED 
(
	[LoginProvider] ASC,
	[ProviderKey] ASC,
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AspNetUserRoles]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AspNetUserRoles](
	[UserId] [nvarchar](128) NOT NULL,
	[RoleId] [nvarchar](128) NOT NULL,
 CONSTRAINT [PK_dbo.AspNetUserRoles] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC,
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AspNetUsers]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AspNetUsers](
	[Id] [nvarchar](128) NOT NULL,
	[Email] [nvarchar](256) NULL,
	[EmailConfirmed] [bit] NOT NULL,
	[PasswordHash] [nvarchar](max) NULL,
	[SecurityStamp] [nvarchar](max) NULL,
	[PhoneNumber] [nvarchar](max) NULL,
	[PhoneNumberConfirmed] [bit] NOT NULL,
	[TwoFactorEnabled] [bit] NOT NULL,
	[LockoutEndDateUtc] [datetime] NULL,
	[LockoutEnabled] [bit] NOT NULL,
	[AccessFailedCount] [int] NOT NULL,
	[UserName] [nvarchar](256) NOT NULL,
	[IsApproved] [bit] NULL,
	[image] [nvarchar](max) NULL,
	[Adress] [nvarchar](max) NULL,
	[status] [nvarchar](max) NULL,
	[Opentime] [time](7) NULL,
	[Closetime] [time](7) NULL,
	[Fullname] [nvarchar](max) NULL,
 CONSTRAINT [PK_dbo.AspNetUsers] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Blog]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blog](
	[Blogid] [int] IDENTITY(1,1) NOT NULL,
	[Bloglong] [varchar](100) NULL,
	[Blogday] [date] NOT NULL,
	[image] [varchar](100) NULL,
	[Blogshort] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Blogid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CartItem]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartItem](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [varchar](100) NULL,
	[Price] [decimal](18, 0) NOT NULL,
	[Image] [varchar](100) NOT NULL,
	[Quantity] [int] NULL,
	[Money] [float] NULL,
	[Productid] [int] NULL,
	[IdUser] [nvarchar](128) NOT NULL,
 CONSTRAINT [PK__CartItem__3214EC27E66069A0] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Categoryid] [int] IDENTITY(1,1) NOT NULL,
	[Categoryname] [nvarchar](100) NULL,
	[image] [nvarchar](max) NULL,
 CONSTRAINT [PK__Category__19060623C1313E61] PRIMARY KEY CLUSTERED 
(
	[Categoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[comment_id] [int] NOT NULL,
	[content] [nvarchar](max) NULL,
	[Store_id] [nvarchar](128) NULL,
	[user_id] [nvarchar](128) NULL,
	[Rating] [int] NULL,
	[created] [datetime] NOT NULL,
	[img] [nvarchar](max) NULL,
	[clip] [nvarchar](max) NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[comment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comment_SP]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comment_SP](
	[id] [int] NOT NULL,
	[content] [nvarchar](max) NULL,
	[product_id] [int] NULL,
	[user_id] [nvarchar](128) NULL,
	[rating] [int] NULL,
	[clip] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
 CONSTRAINT [PK_comment_SP] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[Id] [int] NOT NULL,
	[Code] [nvarchar](50) NULL,
	[StoreId] [nvarchar](128) NULL,
	[SoLuong] [int] NULL,
	[DiscountPercent] [float] NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[extrafood]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[extrafood](
	[ext_id] [int] IDENTITY(1,1) NOT NULL,
	[image] [varchar](100) NULL,
	[Productid] [int] NOT NULL,
	[price] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ext_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Heartitem]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Heartitem](
	[ID] [int] NOT NULL,
	[ProductName] [varchar](100) NULL,
	[Price] [decimal](18, 0) NULL,
	[Image] [varchar](100) NULL,
	[Productid] [int] NULL,
	[Userid] [nvarchar](128) NULL,
 CONSTRAINT [PK_Heartitem] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Message]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[Id] [int] NOT NULL,
	[Content] [nvarchar](max) NULL,
	[Time] [time](7) NULL,
	[Storeid] [nvarchar](128) NULL,
	[Userid] [nvarchar](128) NULL,
	[Img] [nvarchar](max) NULL,
 CONSTRAINT [PK_Message] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_detail]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_detail](
	[Od_id] [int] NOT NULL,
	[Productid] [int] NOT NULL,
	[price] [float] NULL,
	[num] [bigint] NULL,
	[tt_money] [float] NULL,
	[Storeid] [nvarchar](128) NULL,
	[VoucherCode] [nvarchar](50) NULL,
	[Totalinvoucher] [float] NULL,
 CONSTRAINT [PK__Order_de__53C82D04CECAC762] PRIMARY KEY CLUSTERED 
(
	[Od_id] ASC,
	[Productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Od_id] [int] IDENTITY(1,1) NOT NULL,
	[Od_name] [varchar](50) NULL,
	[Od_email] [nvarchar](50) NULL,
	[Od_phone_number] [float] NULL,
	[Od_address] [nvarchar](100) NULL,
	[Od_note] [nvarchar](100) NULL,
	[Od_date] [datetime] NULL,
	[Od_status] [bit] NULL,
	[idthanhtoan] [int] NULL,
	[VoidanOder] [bit] NULL,
 CONSTRAINT [PK__Orders__1888DEAE55BF8437] PRIMARY KEY CLUSTERED 
(
	[Od_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[Productid] [int] IDENTITY(1,1) NOT NULL,
	[Productname] [nvarchar](max) NULL,
	[Soluong] [int] NULL,
	[price] [int] NULL,
	[image] [nvarchar](100) NULL,
	[discription] [nvarchar](max) NULL,
	[Categoryid] [int] NOT NULL,
	[sortdiscription] [nvarchar](max) NULL,
	[Userid] [nvarchar](128) NULL,
	[DateCreated] [date] NULL,
	[status] [bit] NOT NULL,
	[is_hot] [bit] NOT NULL,
	[DiscountedPrice] [int] NULL,
	[DiscountPercent] [int] NULL,
	[DiscountStartTime] [datetime] NULL,
	[DiscountEndTime] [datetime] NULL,
	[Tinhtranggiamgia] [bit] NULL,
	[GiaGiamTheoKhungGio] [int] NULL,
	[phantramgiamgia] [int] NULL,
 CONSTRAINT [PK__Product__B40F3AA58E43BAEA] PRIMARY KEY CLUSTERED 
(
	[Productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThanhToan]    Script Date: 11/24/2023 10:52:01 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThanhToan](
	[id] [int] NOT NULL,
	[TenHinhThuc] [nvarchar](max) NULL,
 CONSTRAINT [PK_ThanhToan] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[__MigrationHistory] ([MigrationId], [ContextKey], [Model], [ProductVersion]) VALUES (N'202303301550417_InitialCreate', N'WebFood.Models.ApplicationDbContext', 0x1F8B0800000000000400DD5CDD6EE33616BE5FA0EF20E8AA5DA4567E7606B381DD2275E26EB0931F8C33DDDE0D68897684914855A2D2048B3ED95EEC23ED2B2C2951B2F82751B6623BC500038B3CFCCEE1E12179787898FFFDE7BFE31F9FE3C8798269166234714F46C7AE03918F8310AD266E4E96DF7F707FFCE19BBF8CAF82F8D9F9A5A23B6374B425CA26EE2321C9B9E765FE238C41368A433FC5195E92918F630F04D83B3D3EFEBB7772E2410AE1522CC7197FCA110963587CD0CF29463E4C480EA21B1CC028E3E5B4665EA03AB7208659027C3871FF0517338C835149E93A175108A81473182D5D072084092054C6F3CF199C9314A3D53CA105207A784920A55B8228835CF6F335B96D378E4F5937BC75C30ACACF3382E39E8027675C2F9EDC7C23EDBAB5DEA8E6AEA886C90BEB75A1BD897B1DC0A2E8138EA8026486E7D32865C413F7A666719125B7908CAA86A312729652B8DF71FA75D4443C72ACDB1DD576743A3A66FF8E9C691E913C851304739282E8C8B9CF1751E8FF13BE3CE0AF104DCE4E16CBB30FEFDE83E0ECFDDFE0D9BB664F695F299D50408BEE539CC094CA069775FF5DC713DB7972C3BA59A34DA9156A4B744AB8CE0D78FE08D18A3CD2C972FAC17566E1330CAA126E5C9F514867106D44D29C7EDEE651041611ACEBBD569EECFF16AEA7EFDE0FC2F5163C85AB62E825FE74E2A4745E7D8251519B3D864939BD84F1FEC2C966298ED9B7685F65ED9739CE539F75061B491E40BA8244946EECAD8DD7CAA419D4F0665DA11EBE69334955F3D692B20E6D32132A16BB9E0D95BCAFCBD7DAE22E92840E5E615A4C236D06276E5423A9E591C3EBD72673626B328876E5CFBC025EC5208C0658022DB850CF6319A631AC7BF913A60607506F99EF4196D11520F807C81E5B44A73F07107D0EFD3CA5863927204E5E9DDBFD2346F0368F17CCDE77C76BB0A179F81DCF804F707A8558ABADF13E62FF2BCEC9150A2E01819F895F01B2CF8730B60718449C0BDF875936A3C60C8329A68E7505788DC8D9696F38B638EDDB059946208CF53E88B48C7EA948D77E889E42F1450C643A7FA44DD48F7815223B512B52B3A82545A7A89CACAFA80CCC4E524E6916B420E894B3A41ACCC32B46687817AF803D7C1F6FBBCDDBB41634D438A72B24FC192298D2652CB80784C014AD47C066DDD887B3500C1F63FAEA7B53C1E91710E543B3DA6836148BC0F0B3A1803DFCD95088498B9FC280792516079F8A98C25BD1EBCF54DD734E926CD7D341E8E6AE99EF660D304D978B2CC37E58CC024DC88B072C44F9A90FE774472FCADEC81110DA316AE821DBF26809ED9B2B1BD51DBA841124D0B9F0CB90E014643E085435D20E053D04AB76548D60EB488828DC5F159ED4D261CA1A017608CAE84C0D1151A74588FC300151A796A496965B18EB7BCD43AEB98409448C61A7266C98EB031F4C809A8F34285D1A1A7B0D8B6B374483D76A1AF32E17763DEE4A3C622736D9E13B1BEC92FB6FAF6298ED1ADB8171B6ABC4460063106F1F06CACF2AB606201F5C0ECD40A51393C140B94BB513031535B607031555F2E60CB43CA2DA8EBF745E3D34F3140FCABBDFD65BD5B507DB14F47160A659FA9EB40DA12D60AA9AE7E58255C267A2399C5139F9F92CE3AEAE6C220C7C0E8918B259FBBB5A3FD46B07918DA80D706D681DA0FCFA4F015226540FE1AA585EAB74DC8BE8015BC5DD5A61F9DA2FC1366C40C56E5E833608CD97A5B2715A9D3EEA9ED5D6A018B9D561A181A3310879F1123B6EA114535C56558C8D2FDCC71B6E748C0F468B823A3C578392AACE0CAEA5CA34BBB5A473C8FAB8645B6949729F0C5AAA3A33B896B88D762B49E314F4700BB65291B8850F34D9AA4847BDDBD47563AFCC8CE20563CF904235BE014912A25523A58A9738F3329F6AFAFDBC7FB2515C62787EA6C939AAA5AD39119C8215946A296B2AE92C4C33720908580016E79906B142A6DD5B0DCB7FC5B2B97DAA8358ED031535FB5DB6902EED857D56754478FB19ED5DCCBC992284AE197B7D7387A5B78108A49AA8FD1447798CCCCE95B9757977D76C5F96A808634F925F719E144D292EAEA876AB415127C4000354FB2D9B0F9219C2A4EACAEB6C2ADBE4899A51AAC05413C514ACDADBA0991C18EB8192FDC2FEE3D489F03AF38927A3340178514F8C463E8302D6A8B34715534E9A98628D3DA29457D28494AA7A48D9CC1E11846C566C8467D0A89EC29E839A2FD244576BED913599234D684DF506D81A99E53A7B544D72491358536D8FBDCE349117D003DEB18CA7958DB6ACF230BBDD9E65C0789DD570982DAF7167DF046A14F7C4E2B7F20A182F3F484B329EE836B2A4327EB19D251930CC2B8E70D32D2E38ADD7F3664CE1FA5A58D4DBAEEFCD78FDECF555AD4239CCC92435F7FA50271DDEC6FC20D5FD484639599524AE53A9916EE82F1981F188118CE6BF45D328846CF9AE086E000A97302365CA867B7A7C722ABDB5399C772F5E960591E6206A7AFC228ED90EB2AFD01348FD4790AAB9105BBC0D59832A61E66B14C0E789FBEFA2D57911B160BF8AE223E73AFB8CC2DF725AF190E6D0F943CDED1C2657DEE275462DE81F6FE2D983BDCAAF7FFD52363D72EE523A9DCE9D6349D19B0CBFF818A2973465D32DA4D9F889C4DB9D6DC23B042DAA345B367F76B008C9204F0E2A29BF8DC1F3777D45D33E2BD80A51F3746028BC4154687A1AB00996F15940403F49F12CA05F67F5CF043611CDF8442044FDC1E40702F6CB50D5728FFB90E6B0B48B25A9D0736782F556D996FBDE9B943CECAD26BA9A6BDD036ED07CEAED5C943796A73CD8D6A949431E0C7B9F76FFEAB9C787926EBC76DAF79B65BCCBC4E2964BA43F553EF10164C069327AF69F35BC6B5B33457F0F3CF5B25F6EF081191BDFE6F79F01BC6B633305880FDCD87AE5F91E98ADED6BFFDCB3A5596FA17BCFDA5513900CB738BA287257566E1972A7C7FF05A646507A94E5634A7D1A585B0A6B07C3358999A939FF4C66AC4C1C85AF42D1CEB65F5FF986DFDA594ED3CED690B5D9C69BAFFFADBC394D3B6F432EE43EF289B5D988BA1CEF8E75AC2D65EA2DE50F0B3DE94857EFF2595BAFE4DF52BAF0204A11668FE176F9ED64070FA29221A74E8F6C60F5A298EE9D8DBFB948F7EF2C5CAD21D85F6044D01776CD9AE61A2D71B5794B1255245284E6061210D02DF52225E112F88456B30074F11ABC08EAB16B90050CAED15D4E929CD02EC3781109012FE604B4F12F529E4599C77709FBCA86E80215336481FB3BF4531E46412DF74C1313324030EF82877BD9581216F65DBDD448B718590271F5D54ED1038C9388826577680E9EE026B251F3FB0857C07F5947004D20DD0321AA7D7C1982550AE28C63ACDBD34F6AC341FCFCC3FF01EBF3DA197A540000, N'6.4.4')
GO
INSERT [dbo].[AspNetRoles] ([Id], [Name]) VALUES (N'1', N'Admin')
INSERT [dbo].[AspNetRoles] ([Id], [Name]) VALUES (N'2', N'Member')
INSERT [dbo].[AspNetRoles] ([Id], [Name]) VALUES (N'3', N'User')
GO
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'17baaff5-4e3d-4fd3-9117-d3807213fad0', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'235c64fa-c07c-4baf-9db3-fb2546d1a80c', N'2')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'2a3ea0f4-356c-4807-bb99-3d180bedf909', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'376deade-d285-4d6c-999c-2dd59b6c6718', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'43394c3b-d937-4044-b556-fb0902a170ee', N'2')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'48e05b61-2bea-4555-a66b-f64a06f36377', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'58eb36b0-19f9-4ec6-9e3d-e1b91aba92ed', N'2')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'7f921c48-9222-42c1-b098-006e36e2ba2f', N'2')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'838e344c-e474-4b8d-ba14-48b9c9b26226', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'95649aa4-128e-4419-b64b-aab6c4973f6d', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'95eb7e77-43dd-464b-aad1-3a1595b45de6', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'9ef0eb0e-85ef-4d67-b642-afa1301a78e4', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'a4b1f274-2863-4d95-8576-b66510f387d8', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'aac43d02-7b78-4e74-9421-ccbe8efb7f4a', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'b66fde3b-1942-4877-81a4-6eb6ca57c1e5', N'2')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'bc8f3c03-8c07-4f28-bb67-0595ebdc17fd', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'f9e50590-5ca0-4db4-96c7-e58d994aa7f7', N'3')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'fa7ba202-55ae-46aa-a360-96f5cbf8d524', N'2')
INSERT [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'fb38ad1f-8fb1-413c-ad33-09fb32e653ec', N'1')
GO
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'ChuShop1@gmail.com', 0, N'AITyqou+rVbe5PZ6YQIw5+1jGI33B8u6yXLPpjc7BdkSaBUEMgZ14iUrcx43dSJ7JQ==', N'4523156b-3022-4b88-b678-a48670151aa2', N'0374059467', 0, 0, NULL, 1, 0, N'ChuShop1@gmail.com', 1, N'ImageProduct10.png', N'Tan Phu A _ Quận 9 Thành Phố Hồ Chí Minh', NULL, CAST(N'01:00:00' AS Time), CAST(N'23:59:00' AS Time), N'NguyenNgocSang')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'17baaff5-4e3d-4fd3-9117-d3807213fad0', N'mayanhmayquayphim@gmail.com', 0, N'AKi3FxApvx3OIDL38HNMI5p4WFHBYS1GNJM1Nlov5PDsH2CalEPT1RZ9dfvJ0fMxqQ==', N'a0649117-b219-49fc-a404-cf1aa592f667', N'123456789', 0, 0, NULL, 1, 0, N'mayanhmayquayphim@gmail.com', 1, NULL, N'đường D1, quận 9, tp Hồ Chí Minh', NULL, CAST(N'07:18:00' AS Time), CAST(N'22:18:00' AS Time), N'mayanhmayquayphim')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'235c64fa-c07c-4baf-9db3-fb2546d1a80c', N'soan01965@gmail.com', 0, N'123456', NULL, NULL, 0, 0, NULL, 1, 0, N'sasa', 1, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'2a3ea0f4-356c-4807-bb99-3d180bedf909', N'thoitrangnu@gmail.com', 0, N'AMWwp/Z4lgnB/ZnOHPeQctF6f7kAKcyHqx7laNX36qYGKRyAlZDmmK7Le444qFK2Qg==', N'6543ed88-fb86-47a8-bf52-0f23b0a40046', N'123456789', 0, 0, NULL, 1, 0, N'thoitrangnu@gmail.com', 1, NULL, N'Man Thiện, quận 9, tp Hồ Chí Minh', NULL, CAST(N'07:20:00' AS Time), CAST(N'20:20:00' AS Time), N'thoitrangnu')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'Store2@gmail.com', 0, N'AAQjkgHhf7YJqX4FSv2kKI8Js1pWWdLRnhpN+uF8pGZVKMjAncn/n6Lp7S2lW4z8Pw==', N'376f7dc7-6209-4027-a9ae-581681402fc3', NULL, 0, 0, NULL, 1, 0, N'Store2@gmail.com', 1, N'bt2.jpg', NULL, NULL, CAST(N'09:30:00' AS Time), CAST(N'15:30:00' AS Time), NULL)
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', N'member124@gmail.com', 0, N'AJA18BOYyvvksdpjOBCOntUGaXNeKa7vkMGgWhjF6lz8Oi9XxsMmbD9wV7WBkDSvaw==', N'3cbae886-a2e2-4130-ab97-251dd63aa03b', N'0374059466', 0, 0, NULL, 1, 0, N'member124@gmail.com', 0, NULL, N'Thôn 3_ Tân Phú A _ Thành Phố Hồ Chí Minh', NULL, NULL, NULL, N'Diêm Thị Diễm Quỳnh ')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'376deade-d285-4d6c-999c-2dd59b6c6718', N'mevabe@gmail.com', 0, N'AB7eYH800NvcOOJcsD690DqfPfZyoTVjBXaM7WN82pgsAKqYnxWLKIWpXX45lAGp9w==', N'fb6afeda-cd5c-4ea9-98ab-f2021936b0da', N'123456789', 0, 0, NULL, 1, 0, N'mevabe@gmail.com', 1, NULL, N'Hoàng Diệu, quận 9, tp Hồ Chí Minh', NULL, CAST(N'00:10:00' AS Time), CAST(N'18:10:00' AS Time), N'mevabe')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'43394c3b-d937-4044-b556-fb0902a170ee', N'nguyenngocsang1682@gmail.com', 0, N'AFZzeHhWX23Qx1t/0gxHSMy7yFhP5occT2+RDtDB99ALBCOrbT2neFAFhBqTNxFcBg==', N'f8795651-8f5b-4509-9b72-1931ef843f2a', N'0374059466', 0, 0, NULL, 1, 0, N'nguyenngocsang1682@gmail.com', 0, NULL, N'39/115 Tăng nhơn phu A -  Thủ Đức', NULL, NULL, NULL, N'Nguyễn Ngọc Sang')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'48e05b61-2bea-4555-a66b-f64a06f36377', N'quanao@gmail.com', 0, N'AIEDVA5+4d/EM6ydrdim+NG4E10Yo5HsikEjla9JwI6ZJVKcCoz40kloBkAG8o6pgg==', N'9714c535-9c34-4519-840d-9c9e9635bb30', N'123456789', 0, 0, NULL, 1, 0, N'quanao@gmail.com', 1, NULL, N'Lê Văn Việt, quận 9, tp Hồ Chí Minh', NULL, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time), N'quanao')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'58eb36b0-19f9-4ec6-9e3d-e1b91aba92ed', N'taoxn306@gmail.com', 0, N'AKJKABDjOueAV/TTVXOuGbQDI8tzquF0IFBbh1y35QiaZYNm8Bn3Kv0GXHTAYFs9ig==', N'020de3bf-4e4e-4f05-9a25-a7972917cd68', N'0374059466', 0, 0, NULL, 1, 0, N'2023 - My ASP', 0, NULL, N'Tan Phu A _ Quận 9 Thành Phố Hồ Chí Minh', NULL, NULL, NULL, N'NguyenNgocSang')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'7f921c48-9222-42c1-b098-006e36e2ba2f', N'Sang@gmail.com', 0, N'123456', NULL, NULL, 0, 0, NULL, 1, 0, N'NgocSang', 1, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'838e344c-e474-4b8d-ba14-48b9c9b26226', N'thietbidientu@gmail.com', 0, N'AEiqxv3o/o0JpP9pbpu3wwa38zfzW77XYba6oJEHcX+SpsYJxxODKSMKl5NC/FamUQ==', N'6f0a8d27-0ae0-4ae0-bf28-27a02937dd0e', N'123456789', 0, 0, NULL, 1, 0, N'thietbidientu@gmail.com', 1, NULL, N'đường số 14, quận 9, tp Hồ Chí Minh', NULL, CAST(N'06:12:00' AS Time), CAST(N'10:12:00' AS Time), N'thietbidientu')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'95649aa4-128e-4419-b64b-aab6c4973f6d', N'nhacuavadoisong@gmail.com', 0, N'APTx5vXOAfn4B2PZiWiD7IoHvxsykfYFXxucgr6zNaBQDVnHpIPmfZlpUyotwQlaXw==', N'3929dbb2-681c-42d6-8b61-d52740db77f9', N'123456789', 0, 0, NULL, 1, 0, N'nhacuavadoisong@gmail.com', 1, NULL, N'ngã tư hàng xanh, quận Bình Thạnh, tp Hồ Chí Minh', NULL, CAST(N'06:13:00' AS Time), CAST(N'22:14:00' AS Time), N'nhacuavadoisong')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'95eb7e77-43dd-464b-aad1-3a1595b45de6', N'ChuShop2@gmail.com', 0, N'ACFxeyPfB1IeRf+8EHiunQnN2BDAOJwg5jpncjbSuKxdKvT1FsbeWFNdq//roFEMLw==', N'17bd9c40-99dc-4abc-bb6b-af654a21c6b3', N'0374059466', 0, 0, NULL, 1, 0, N'ChuShop2@gmail.com', 1, NULL, N'Tan phu A', NULL, CAST(N'14:14:00' AS Time), CAST(N'20:15:00' AS Time), N'Nguyenngocsang')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'9ef0eb0e-85ef-4d67-b642-afa1301a78e4', N'ChuShop3@gmail.com', 0, N'AFOLVduIMeq945pVzeCNrgTcQTJ/aQqx3WO3hiM5dDGrAqWBhG27pfY/gffkI0g0wQ==', N'd60f066e-710c-4439-b3ae-08e8a8585618', N'0374059466', 0, 0, NULL, 1, 0, N'ChuShop3@gmail.com', 1, NULL, N'Tan Phu A _ Quận 9 Thành Phố Hồ Chí Minh', NULL, CAST(N'00:45:00' AS Time), CAST(N'04:46:00' AS Time), N'NguyenNgocSang')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'a4b1f274-2863-4d95-8576-b66510f387d8', N'sacdep@gmail.com', 0, N'AGb0o0rv65WcNuzKPuyRBfzVH5wWt1G5OrEEF8dffjklRZhV4c7HCEbA6Frw3Ei+og==', N'36b2fad0-e3ef-4295-8cdf-91ccdc9a3eaa', N'123456789', 0, 0, NULL, 1, 0, N'sacdep@gmail.com', 1, NULL, N'đường D2, quận 9, tp Hồ Chí Minh', NULL, CAST(N'10:17:00' AS Time), CAST(N'22:17:00' AS Time), N'sacdep')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'aac43d02-7b78-4e74-9421-ccbe8efb7f4a', N'suckhoe@gmail.com', 0, N'AJDm5FMMXiNv2iuCKz1wcCUlHxB77IZaUIYWasTdIYkNJuD8WWdUkYAmNJuIvoOHvg==', N'7d4df102-ad47-4638-9d96-a35fe009d24e', N'123456789', 0, 0, NULL, 1, 0, N'suckhoe@gmail.com', 1, NULL, N'Đình Phong Phú, quận 9, tp Hồ Chí Minh', NULL, CAST(N'10:19:00' AS Time), CAST(N'12:19:00' AS Time), N'suckhoe')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'b66fde3b-1942-4877-81a4-6eb6ca57c1e5', N'nguyensang@gmail.com', 0, N'pXL0IKqn8tiuid9U4Pht2zZiL8JUZOazRjVgBHz8rFM=
', NULL, NULL, 0, 0, NULL, 1, 0, N'nguyensang@gmail.com', 1, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'bc8f3c03-8c07-4f28-bb67-0595ebdc17fd', N'dienthoaiphukien@gmail.com', 0, N'AHdAMPDg0pX5+lB+5OwiURAw1tVbjx2W4HF90mQ6Lp3A1EqeEVzYFZ5OvNq2O/QQMA==', N'0309d76b-15d5-4f99-b97e-1e0692c415e7', N'123456789', 0, 0, NULL, 1, 0, N'dienthoaiphukien@gmail.com', 1, NULL, N'đường D2, quận Bình Thạnh, tp Hồ Chí Minh', NULL, CAST(N'08:00:00' AS Time), CAST(N'21:10:00' AS Time), N'dienthoaiphukien')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'f9e50590-5ca0-4db4-96c7-e58d994aa7f7', N'laptopmaytinh@gmail.com', 0, N'ANojTjwv7wWoV2uENbJ6j0EZ9f+dm4wawsga+Rz7+3eQ00wPiUgKVGKriPAjhA8ygw==', N'62396b94-446d-4241-8d1c-07e4dab50965', N'123456789', 0, 0, NULL, 1, 0, N'laptopmaytinh@gmail.com', 1, NULL, N'Xô Viết Nghệ Tĩnh, quận Bình Thạnh, tp Hồ Chí Minh', NULL, CAST(N'08:15:00' AS Time), CAST(N'22:15:00' AS Time), N'laptopmaytinh')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'fa7ba202-55ae-46aa-a360-96f5cbf8d524', N'dnhuw@gmail.com', 0, N'123456', N'89a4209e-ea6c-44e0-aff1-5a9520bee8f3', N'0374059466', 0, 0, NULL, 1, 0, N'duong nhu', 0, NULL, N'Tan Phu A _ Quận 9 Thành Phố Hồ Chí Minh', NULL, NULL, NULL, N'Duuong nhu')
INSERT [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [IsApproved], [image], [Adress], [status], [Opentime], [Closetime], [Fullname]) VALUES (N'fb38ad1f-8fb1-413c-ad33-09fb32e653ec', N'admin@gmail.com', 0, N'APMkG37KJSBm1V4rdTUs2jV4iVBkLVDLxvUzE/YqtfhebrzZMVW05txDkt36i7XUjQ==', N'1acba9d8-dd1b-412f-9283-c37022999d7c', NULL, 0, 0, NULL, 1, 0, N'admin@gmail.com', 0, NULL, NULL, NULL, NULL, NULL, N'Diem Quynh')
GO
SET IDENTITY_INSERT [dbo].[Blog] ON 

INSERT [dbo].[Blog] ([Blogid], [Bloglong], [Blogday], [image], [Blogshort]) VALUES (1, N'adddddddddd', CAST(N'2023-01-01' AS Date), N'blog1.jpg', N'adddaaaaaa')
INSERT [dbo].[Blog] ([Blogid], [Bloglong], [Blogday], [image], [Blogshort]) VALUES (2, N'addd', CAST(N'2023-02-02' AS Date), N'blog2.jpg', N'daaaa')
INSERT [dbo].[Blog] ([Blogid], [Bloglong], [Blogday], [image], [Blogshort]) VALUES (3, N'aaaaaaaaaaaaa', CAST(N'2022-03-03' AS Date), N'blog3.jpg', N'daaaaagggggggggg')
INSERT [dbo].[Blog] ([Blogid], [Bloglong], [Blogday], [image], [Blogshort]) VALUES (4, N'hddddddddddddddddddd', CAST(N'2022-02-16' AS Date), N'blog2.jpg', N'hom nay an gi')
INSERT [dbo].[Blog] ([Blogid], [Bloglong], [Blogday], [image], [Blogshort]) VALUES (18, N'<p>adaddddddddd</p>', CAST(N'2023-06-21' AS Date), N'0.jpg', N'Vi?t Béo ')
SET IDENTITY_INSERT [dbo].[Blog] OFF
GO
SET IDENTITY_INSERT [dbo].[CartItem] ON 

INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6436, N'Th?t Rán', CAST(50000 AS Decimal(18, 0)), N'ImageProduct1.jpg', 2, NULL, 1, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6437, N'Kem Dâu', CAST(45000 AS Decimal(18, 0)), N'ImageProduct3.jpg', 1, NULL, 3, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6438, N'Pizza', CAST(500000 AS Decimal(18, 0)), N'4.png', 1, NULL, 14, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6441, N'Gà Chua Ng?t', CAST(9000 AS Decimal(18, 0)), N'ImageProduct6.png', 1, NULL, 6, N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6444, N'Kem Dâu', CAST(45000 AS Decimal(18, 0)), N'ImageProduct3.jpg', 2, NULL, 3, N'117bdf44-dd82-4050-8eab-070a493e6f11')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6456, N'Bánh Nhi?u L?p', CAST(55000 AS Decimal(18, 0)), N'ImageProduct4.jpg', 6, NULL, 4, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6457, N'Th?t Rán', CAST(50000 AS Decimal(18, 0)), N'ImageProduct1.jpg', 8, NULL, 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6458, N'Th?t Rán', CAST(50000 AS Decimal(18, 0)), N'ImageProduct1.jpg', 1, NULL, 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6461, N'Gà Chua Ng?t', CAST(30000 AS Decimal(18, 0)), N'ImageProduct6.png', 5, NULL, 6, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6462, N'CoCa', CAST(15000 AS Decimal(18, 0)), N'ImageProduct12.jpg', 2, NULL, 12, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (6463, N'Kem Dâu', CAST(100000 AS Decimal(18, 0)), N'ImageProduct3.jpg', 2, NULL, 3, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (7471, N'Kem Dâu', CAST(100000 AS Decimal(18, 0)), N'ImageProduct3.jpg', 1, NULL, 3, N'fa7ba202-55ae-46aa-a360-96f5cbf8d524')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (7472, N'banh duc', CAST(150000 AS Decimal(18, 0)), N'19.png', 1, NULL, 13, N'fa7ba202-55ae-46aa-a360-96f5cbf8d524')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (7546, N'Bánh Nhi?u L?p', CAST(27500 AS Decimal(18, 0)), N'ImageProduct4.jpg', 1, NULL, 4, N'43394c3b-d937-4044-b556-fb0902a170ee')
INSERT [dbo].[CartItem] ([Id], [ProductName], [Price], [Image], [Quantity], [Money], [Productid], [IdUser]) VALUES (7553, N'Cúp p', CAST(125000 AS Decimal(18, 0)), N'ImageProduct84.png', 1, NULL, 84, N'fa7ba202-55ae-46aa-a360-96f5cbf8d524')
SET IDENTITY_INSERT [dbo].[CartItem] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (1, N'Salad', N'slj.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (2, N'Seafood', N'sf.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (3, N'Drinks', N'd.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (4, N'Pasta&Pizza', N'pzlt.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (13, N'Kem ', N'k.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (14, N'Rán', N'r.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (15, N'Bánh', N'b1.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (16, N'Nướng', N'n.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (17, N'Món Nước ', N'0.jpg')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (19, N'Quần Áo', N'0.PNG')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (20, N'Điện Thoại & Phụ Kiện', N'0.PNG')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (21, N'Thời Trang Nữ', N'0.PNG')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (22, N'Mẹ và Bé', N'0.PNG')
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (23, N'Thiết Bị Điện Tử', NULL)
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (24, N'Nhà Cửa và Đời Sống', NULL)
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (25, N'Laptop và Máy Tính', NULL)
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (26, N'Sắc Đẹp', NULL)
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (27, N'Máy Ảnh và Máy Quay Phim', NULL)
INSERT [dbo].[Category] ([Categoryid], [Categoryname], [image]) VALUES (28, N'Sức Khỏe', NULL)
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (8, N'tuyet voi ', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 5, CAST(N'2023-05-24T03:56:29.290' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (9, N'oos sau ke', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-05-24T03:59:47.493' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (10, N'tuyetj nhat roif ', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-05-30T17:18:54.963' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (12, N'heheheheh', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, CAST(N'2023-05-30T17:23:44.757' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (13, N'ưddqd', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 5, CAST(N'2023-05-30T17:23:55.320' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (17, N'đâ', N'9e7c6fb5-6931-48b5-9b58-b5bf72193d69', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, CAST(N'2023-05-30T17:26:45.300' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (18, N'met nhưo ', N'9e7c6fb5-6931-48b5-9b58-b5bf72193d69', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, CAST(N'2023-05-30T17:27:59.093' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (19, N'adaddadad', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-05-30T17:35:24.610' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (20, N'hdaddqhdojaphodwh', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-01T22:41:39.440' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (21, N'hdhdhah', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-02T00:03:40.887' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (22, N'dhahdha', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-02T09:42:45.483' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (23, N'oce', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, CAST(N'2023-06-05T09:04:21.630' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (25, N'oce', N'fb298d76-6693-4456-8733-c580579d2f26', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-05T09:05:09.850' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (26, N'dadad', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 5, CAST(N'2023-06-08T22:31:35.357' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (27, N'sanghehheheheheh', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, CAST(N'2023-06-08T22:32:08.387' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (29, N'adadada', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-09T09:21:14.577' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (30, N'hadhaudh', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-09T09:40:43.190' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (34, N'ngon lam', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-06-21T06:01:39.160' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (37, N'shop rất chất lượng
', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-10-05T13:16:56.107' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (38, N'2023 - My ASP', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-10-05T13:26:02.403' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (39, N'2023 - My ASP', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-10-05T13:54:06.927' AS DateTime), N'/Content/products/f24061d2-0c38-48e7-b341-06d864dfa983.jpg', NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (40, N'dddddddddddddddddddddddddddddddddddddd', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-10-05T14:00:51.803' AS DateTime), N'/Content/products/72e680e8-2e0b-4cd2-b8d6-f51bbe3ccea8.jpg', NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (41, N'tuyeetj doif', N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, CAST(N'2023-10-05T15:18:38.087' AS DateTime), N'/Content/products/82616d70-f9cd-4787-a920-27ca715f2cbf.jpeg', NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (42, N'rất cảm ơn ề món hàng shop uy tín ', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 5, CAST(N'2023-10-06T08:28:23.483' AS DateTime), N'/Content/products/3f16ea9c-65b2-4ed9-b7ed-c23832740c7a.jpeg', NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (43, N'chán shop quá lỗi nhiều ', N'95eb7e77-43dd-464b-aad1-3a1595b45de6', N'43394c3b-d937-4044-b556-fb0902a170ee', 4, CAST(N'2023-10-08T15:53:33.487' AS DateTime), NULL, NULL)
INSERT [dbo].[Comment] ([comment_id], [content], [Store_id], [user_id], [Rating], [created], [img], [clip]) VALUES (44, N'hihihihih', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', 4, CAST(N'2023-10-22T02:03:52.023' AS DateTime), N'/Content/products/294d4414-78a8-45c8-a1e6-407024990908.jpg', NULL)
GO
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (1, N'asdad', NULL, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/6a32a5cf-32a3-444d-9f23-fa40a624f39b.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (2, N'addda', NULL, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, NULL, N'/Content/products/d421bf79-7995-4445-8a34-c3dbc578d642.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (3, N'adada', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, NULL, N'/Content/products/081144ef-1ca7-472c-9827-6846026c5f2d.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (4, N'dâdadad', 3, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/fe0c247b-87e9-4c65-8efa-55fe45e5436f.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (5, N'ADADAD', 4, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 3, NULL, N'/Content/products/c756ae16-31f4-482d-9d00-209b60fd2af1.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (6, N'hợp lý ', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/476bf766-4d88-4cc1-a9a9-c4d87eb23751.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (7, N'hợp lý', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/a99b664d-90da-4d0d-abc0-c871fe96b305.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (8, N'hợp lý', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/04a9c901-1b70-446c-aabb-35f910cd7670.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (9, N'hợp lý', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/5253865c-55ac-4adc-8d92-745bf77bcffb.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (10, N'qadad', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 5, NULL, NULL)
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (11, N'dâdad', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/c724af7b-d568-4937-99c1-316751f84693.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (12, N'dqdqdqqeqeqe', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad', 4, NULL, N'/Content/products/5ba473c4-8fc4-408b-9640-0c7b474486a3.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (13, N'shopp rất là oce xịn cò con bò =))', 439, N'43394c3b-d937-4044-b556-fb0902a170ee', 4, NULL, N'/Content/products/e7d997df-007b-4e1e-a42c-4a7940e616a1.jpeg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (14, N'sản phẩm rất tốt', 1, N'43394c3b-d937-4044-b556-fb0902a170ee', 5, NULL, NULL)
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (15, NULL, 1, N'43394c3b-d937-4044-b556-fb0902a170ee', 5, NULL, N'/Content/products/c991b881-5d5d-4b77-b63e-5d22bde2565e.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (16, N's', 1, N'43394c3b-d937-4044-b556-fb0902a170ee', 5, NULL, N'/Content/products/47d1a65c-07c1-4fdb-9b10-8c80436bbb21.jpg')
INSERT [dbo].[comment_SP] ([id], [content], [product_id], [user_id], [rating], [clip], [image]) VALUES (17, N'khá oce', 84, N'fa7ba202-55ae-46aa-a360-96f5cbf8d524', 4, NULL, NULL)
GO
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (8, N'24a753ff', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', -3, 77, CAST(N'2023-06-06' AS Date), CAST(N'2024-04-12' AS Date), 0)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (11, N'70d55787', N'311f25d5-ea90-4f36-a596-1d573d7033d3', 5, 70, CAST(N'2023-06-11' AS Date), CAST(N'2024-04-12' AS Date), 1)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (13, N'c8f2e8c5', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', -1, 90, CAST(N'2023-06-20' AS Date), CAST(N'2025-04-12' AS Date), 1)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (14, N'0ca78153', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', 4, 10, CAST(N'2023-06-21' AS Date), CAST(N'2024-04-12' AS Date), 1)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (15, N'6ed005e6', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', 5, 30, CAST(N'2023-06-21' AS Date), CAST(N'2024-12-02' AS Date), 1)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (16, N'f6e69b6f', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', 0, 19, CAST(N'2023-06-21' AS Date), CAST(N'2024-04-12' AS Date), 1)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (17, N'33501b61', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', 1, 25, CAST(N'2023-10-27' AS Date), CAST(N'2025-04-12' AS Date), 1)
INSERT [dbo].[Discount] ([Id], [Code], [StoreId], [SoLuong], [DiscountPercent], [StartDate], [EndDate], [Status]) VALUES (18, N'5242e4c5', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', 7, 55, CAST(N'2023-11-10' AS Date), CAST(N'2023-12-12' AS Date), 1)
GO
SET IDENTITY_INSERT [dbo].[extrafood] ON 

INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (23, N'439_img0.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (24, N'439_img1.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (25, N'439_img2.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (26, N'439_img3.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (27, N'439_img4.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (28, N'439_img5.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (29, N'439_img6.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (30, N'439_img7.jpeg', 439, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (31, N'440_img0.jpg', 440, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (32, N'440_img1.jpg', 440, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (33, N'440_img2.jpg', 440, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (69, N'84_img1.png', 84, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (70, N'84_img2.png', 84, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (74, N'441_img0.jpeg', 441, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (75, N'441_img1.jpeg', 441, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (76, N'441_img2.jpeg', 441, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (77, N'441_img3.jpeg', 441, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (79, N'442_img0.jpeg', 442, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (80, N'442_img1.jpeg', 442, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (81, N'442_img2.png', 442, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (82, N'442_img3.jpeg', 442, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (83, N'442_img4.png', 442, NULL)
INSERT [dbo].[extrafood] ([ext_id], [image], [Productid], [price]) VALUES (84, N'442_img5.jpeg', 442, NULL)
SET IDENTITY_INSERT [dbo].[extrafood] OFF
GO
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (10, NULL, CAST(50000 AS Decimal(18, 0)), N'ImageProduct1.jpg', 1, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (11, NULL, CAST(45000 AS Decimal(18, 0)), N'ImageProduct3.jpg', 3, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (12, NULL, CAST(42000 AS Decimal(18, 0)), N'ImageProduct10.jpg', 10, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (13, NULL, CAST(80000 AS Decimal(18, 0)), N'ImageProduct9.jpg', 9, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (15, NULL, CAST(30000 AS Decimal(18, 0)), N'ImageProduct6.png', 6, N'fb38ad1f-8fb1-413c-ad33-09fb32e653ec')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (20, NULL, CAST(25000 AS Decimal(18, 0)), N'0.jpg', 84, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (23, NULL, CAST(30000 AS Decimal(18, 0)), N'ImageProduct6.png', 6, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (24, NULL, CAST(55000 AS Decimal(18, 0)), N'ImageProduct4.jpg', 4, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (25, NULL, CAST(80000 AS Decimal(18, 0)), N'ImageProduct9.jpg', 9, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (26, NULL, CAST(30000 AS Decimal(18, 0)), N'ImageProduct6.png', 6, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (28, NULL, CAST(55000 AS Decimal(18, 0)), N'ImageProduct4.jpg', 4, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (29, NULL, CAST(30000 AS Decimal(18, 0)), N'ImageProduct6.png', 6, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (30, NULL, CAST(80000 AS Decimal(18, 0)), N'ImageProduct9.jpg', 9, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (31, NULL, CAST(500000 AS Decimal(18, 0)), N'4.png', 14, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (32, NULL, CAST(100000 AS Decimal(18, 0)), N'19.png', 13, N'dc269f5d-816f-4024-b157-edc1febca051')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (33, NULL, CAST(700000 AS Decimal(18, 0)), N'14.png', 16, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (34, NULL, CAST(55000 AS Decimal(18, 0)), N'ImageProduct4.jpg', 4, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (35, NULL, CAST(500000 AS Decimal(18, 0)), N'4.png', 14, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (36, NULL, CAST(50000 AS Decimal(18, 0)), N'6.png', 15, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (37, NULL, CAST(100000 AS Decimal(18, 0)), N'19.png', 13, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (38, NULL, CAST(25000 AS Decimal(18, 0)), N'ImageProduct86.jpg', 86, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (39, NULL, CAST(20000 AS Decimal(18, 0)), N'ImageProduct91.jpg', 91, NULL)
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (40, NULL, CAST(50000 AS Decimal(18, 0)), N'ImageProduct1.jpg', 1, N'3510f936-6109-4bc1-a946-0b9fd1bf68ad')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (41, NULL, CAST(45000 AS Decimal(18, 0)), N'ImageProduct3.jpg', 3, N'311f25d5-ea90-4f36-a596-1d573d7033d3')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (42, NULL, CAST(30000 AS Decimal(18, 0)), N'ImageProduct6.png', 6, N'43394c3b-d937-4044-b556-fb0902a170ee')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (43, NULL, CAST(55000 AS Decimal(18, 0)), N'ImageProduct4.jpg', 4, N'43394c3b-d937-4044-b556-fb0902a170ee')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (44, NULL, CAST(700000 AS Decimal(18, 0)), N'14.png', 16, N'43394c3b-d937-4044-b556-fb0902a170ee')
INSERT [dbo].[Heartitem] ([ID], [ProductName], [Price], [Image], [Productid], [Userid]) VALUES (45, NULL, CAST(50000 AS Decimal(18, 0)), N'ImageProduct12.jpg', 12, N'43394c3b-d937-4044-b556-fb0902a170ee')
GO
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (1, N'chao shop', NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (2, N'can tro giup ', NULL, N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (3, N'sao vậy', NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (4, N'sa', CAST(N'18:12:32.1136637' AS Time), N'311f25d5-ea90-4f36-a596-1d573d7033d3', N'43394c3b-d937-4044-b556-fb0902a170ee', N'/Content/products/ebb63be8-c8a2-4f38-80c6-74ce06d049ca.jpg')
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (5, N's', CAST(N'18:16:07.1837741' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (6, N's', CAST(N'18:16:17.2909558' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (7, N'ssss', CAST(N'18:20:34.5491788' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', N'/Content/products/fb327ba6-ccd9-423f-8334-7a995d867b56.jpg')
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (8, N'hú', CAST(N'23:13:12.4163165' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (9, N'eee', NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (10, N's', CAST(N'00:01:50.2861937' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (11, N's', NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (12, N'addad', CAST(N'00:02:03.1537141' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (13, N'yé i do', NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (14, N'chào shop', CAST(N'12:18:51.5058713' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (15, N'dadada', CAST(N'18:26:26.9168348' AS Time), N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
INSERT [dbo].[Message] ([Id], [Content], [Time], [Storeid], [Userid], [Img]) VALUES (16, N'dadadad', NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'43394c3b-d937-4044-b556-fb0902a170ee', NULL)
GO
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (5410, 6, 15000, 2, 30000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', NULL, NULL)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6498, 11, 50000, 1, 50000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'24a753ff', 11500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6504, 12, 50000, 1, 50000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'0ca78153', 45000)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6507, 4, 27500, 1, 27500, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'24a753ff', 6325)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6508, 84, 25000, 2, 50000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'c8f2e8c5', 2500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6512, 84, 12500, 2, 25000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'c8f2e8c5', 2500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6513, 3, 100000, 2, 200000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'c8f2e8c5', 20000)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6515, 4, 27500, 2, 55000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'c8f2e8c5', 5500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6516, 6, 22500, 1, 22500, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'f6e69b6f', 18225)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6517, 4, 27500, 2, 55000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'f6e69b6f', 64800)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6517, 84, 12500, 2, 25000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'f6e69b6f', 64800)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6518, 6, 22500, 2, 45000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'c8f2e8c5', 4500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6519, 84, 12500, 1, 12500, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', NULL, NULL)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6522, 3, 100000, 1, 100000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'c8f2e8c5', 4500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6523, 3, 100000, 2, 200000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'f6e69b6f', 162000)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6524, 86, 25000, 1, 25000, N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', NULL, NULL)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6525, 89, 40000, 1, 40000, N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', N'f6e69b6f', 0)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6539, 84, 250000, 1, 250000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'33501b61', 187500)
INSERT [dbo].[Order_detail] ([Od_id], [Productid], [price], [num], [tt_money], [Storeid], [VoucherCode], [Totalinvoucher]) VALUES (6540, 3, 100000, 1, 100000, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', N'24a753ff', 23000)
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (5410, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-06-21T08:20:44.077' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6498, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T12:21:58.353' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6504, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T14:12:08.330' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6507, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T14:41:13.990' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6508, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T16:47:19.283' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6509, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:13:15.370' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6510, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:14:13.733' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6511, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:14:45.783' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6512, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:15:44.393' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6513, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:17:28.650' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6514, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:17:32.440' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6515, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:17:55.627' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6516, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:34:51.523' AS DateTime), 0, 2, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6517, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-10T17:36:26.250' AS DateTime), 0, 2, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6518, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-11T09:57:19.400' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6519, N'58eb36b0-19f9-4ec6-9e3d-e1b91aba92ed', NULL, NULL, NULL, NULL, CAST(N'2023-11-11T10:44:21.413' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6520, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-11T10:45:17.140' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6521, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-11T10:45:26.010' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6522, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-11T10:45:43.640' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6523, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-11T10:51:33.987' AS DateTime), 0, 1, 0)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6524, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-16T00:30:46.380' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6525, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-16T05:04:29.287' AS DateTime), 0, 2, 0)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6536, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-16T17:21:22.527' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6537, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-16T17:21:36.967' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6538, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-16T18:57:29.653' AS DateTime), 0, 2, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6539, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-17T12:45:57.783' AS DateTime), 0, 1, 1)
INSERT [dbo].[Orders] ([Od_id], [Od_name], [Od_email], [Od_phone_number], [Od_address], [Od_note], [Od_date], [Od_status], [idthanhtoan], [VoidanOder]) VALUES (6540, N'43394c3b-d937-4044-b556-fb0902a170ee', NULL, NULL, NULL, NULL, CAST(N'2023-11-23T02:44:41.013' AS DateTime), 0, 1, 1)
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (1, N'Thịt Rán', 1, 50000, N'ImageProduct1.jpg', N'Ít Dẫu Mỡ, Tiết Kiệm', 14, N'Nguyên Liệu Tự Nhiên, Chế biến sạch sẽ ', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 0, 32500, 35, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (3, N'Kem Dâu', 1, 100000, N'ImageProduct3.jpg', N'Dâu Thực 99%', 13, N'Làm từ dâu Đà Lạt', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (4, N'Bánh Nhiều Lớp', 95, 55000, N'ImageProduct4.jpg', NULL, 15, NULL, N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 1, 27500, 50, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (6, N'Gà Chua Ngọt', 0, 30000, N'ImageProduct6.png', N'Làm từ gà thật', 14, N'Siêu sạch và ngon', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 1, 22500, 25, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (9, N'Cá Hồi Nướng', 0, 80000, N'ImageProduct9.jpg', N'Làm từ cá hồi đại dương', 16, N'Nguyên Liệu thiên nhiên', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 1, 80000, 0, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (10, N'Nước Ép Dâu', 0, 42000, N'ImageProduct10.jpg', N'Làm Từ dâu đà lạt', 3, N'Dâu thật', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 0, 42000, 0, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (11, N'Salaz Cá Ngừ', 9, 50000, N'ImageProduct11.PNG', N'Thích Hợp cho buổi sáng', 1, N'dada', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 1, NULL, NULL, CAST(N'2023-10-30T03:13:00.000' AS DateTime), CAST(N'2023-10-31T03:13:00.000' AS DateTime), 0, 11500, 77)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (12, N'CoCa', 9, 50000, N'ImageProduct12.jpg', N'adad', 3, N'X', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 0, 50000, 0, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (13, N'banh duc', 12, 150000, N'19.png', N'fafaf', 1, N'âffff', N'311f25d5-ea90-4f36-a596-1d573d7033d3', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (14, N'Pizza', 3, 500000, N'4.png', N'sieu ngon sieu re', 3, N'ai biết gì đâu ', N'311f25d5-ea90-4f36-a596-1d573d7033d3', CAST(N'2023-10-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (15, N'Pizza', 2, 50000, N'6.png', N'hcahcagua', 4, N'âf', N'311f25d5-ea90-4f36-a596-1d573d7033d3', CAST(N'2023-10-16' AS Date), 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (16, N'salaz', 14, 700000, N'14.png', N'dâd', 1, N'dadad', N'311f25d5-ea90-4f36-a596-1d573d7033d3', CAST(N'2023-10-16' AS Date), 1, 1, 0, 100, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (17, N'sanwith', 8, 10000000, N'16.png', N'adadad', 4, N'adadad', N'311f25d5-ea90-4f36-a596-1d573d7033d3', CAST(N'2023-10-16' AS Date), 1, 0, 3000000, 70, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (18, N'chicken7vi', 4, 780000, N'21.png', N'eqeqe', 4, N'qeqe', N'311f25d5-ea90-4f36-a596-1d573d7033d3', CAST(N'2023-10-16' AS Date), 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (79, N'salaz hỗn hợp', 6, 20000, N'0.PNG', NULL, 1, NULL, N'fb298d76-6693-4456-8733-c580579d2f26', CAST(N'2023-10-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (84, N'Cúp p', 94, 250000, N'ImageProduct84.png', N'làm từ gà thật nguyên chất ', 28, N'Gà rán món ăn yêu thích của nhiều người đặc biệt là các bạn nhỏ.!', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-10-16' AS Date), 1, 0, NULL, NULL, CAST(N'2023-11-14T05:13:00.000' AS DateTime), CAST(N'2023-11-15T05:14:00.000' AS DateTime), 0, 125000, 50)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (86, N'Phở Tái ', 7, 25000, N'ImageProduct86.jpg', N'Bỏ túi công thức nấu phở bò tái thơm ngon tại nhà – Digifood', 17, N'Phở bò tái .', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-10-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (87, N'gà ran', 5, 50000, N'ImageProduct87.jpg', N'2 miếng gà rán', 14, N'Gà rán giòn đơn giản với lớp da giòn rụm và hương thịt gà mềm thơm', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-10-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (88, N'Salad trứng', 4, 80000, N'ImageProduct88.jpg', N'rau trộn với trứng', 1, N'salad rau quả trộn dầu giấm rất đơn giản mà lại cực kỳ ngon và đẹp mắt.', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (89, N'Pizza', 3, 40000, N'ImageProduct89.jpg', N'pizza phô mai', 4, N'pizza đế mỏng với lớp phô mai béo ngậy', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (90, N'Cơm tấm', 1, 40000, N'ImageProduct90.jpg', N'Cơm tấm thập cẩm', 16, N'Cơm tấm với đủ loại đồ ăn kèm theo', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (91, N'Bánh tráng trộn', 3, 20000, N'ImageProduct91.jpg', N'Bánh tráng trộn với trứng cút', 1, N'Bánh tráng được thêm đủ loại topping', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (92, N'Bánh mì ', 3, 20000, N'ImageProduct92.jpg', N'Bánh mì thập cẩm', 15, N'Bánh mì với đầy đủ thịt nguội và gia vị', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (93, N'Mì trộn', 4, 30000, N'ImageProduct93.jpg', N'Mì trộn tương đen với trứng', 4, N'Mì trộn với sốt tương đen thơm ngon và lát trứng béo ngậy', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (94, N'Mì hoành thánh', 4, 35000, N'ImageProduct94.jpg', N'Mì, hoành thánh, xá xíu', 4, N'Mì hoành thánh được ăn kèm với tương giám đỏ', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (95, N'Kem', 4, 20000, N'ImageProduct95.jpg', N'Kem vị vani', 13, N'Kem với hương vị vani truyền thống', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (96, N'Thịt nướng', 4, 30000, N'ImageProduct96.jpg', N'5 que thịt nướng', 16, N'thịt nướng được thấm với nước sốt BQ truyền thống', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (97, N'Nước ép dưa hấu', 4, 15000, N'ImageProduct97.jpg', N'Nước ép dưa hấu 400ml', 3, N'Nước ép dưa hấu giải nhiệt cho những ngày hè', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (98, N'Sò điệp', 4, 35000, N'0.jpg', N'Sò điệp, phô mai', 2, N'Sò điệp được nướng kèm với sốt phô mai', N'a9ddc7bb-b61a-4523-831d-34c8c2f7ba3c', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (439, N'【Ready Stock】D13 / 116 Plus Smart Watch Bluetooth Waterproof Sports Watch Smartwatch Heart Rate Monitor Blood Pressure Watches', 1000, 2000000, N'439.jpeg', N'<h2 class="Iv7FJp" style="background: rgba(0, 0, 0, 0.02); color: rgba(0, 0, 0, 0.87); font-size: 1.125rem; font-weight: 400; margin: 0px; padding: 0.875rem; text-transform: capitalize; font-family: Roboto, ''Helvetica Neue'', Helvetica, Arial, 文泉驛正黑, ''WenQuanYi Zen Hei'', ''Hiragino Sans GB'', ''儷黑 Pro'', ''LiHei Pro'', ''Heiti TC'', 微軟正黑體, ''Microsoft JhengHei UI'', ''Microsoft JhengHei'', sans-serif;">Product Description</h2><div class="MCCLkq" style="margin: 1.875rem 0.9375rem 0.9375rem; color: rgba(0, 0, 0, 0.8); font-family: Roboto, ''Helvetica Neue'', Helvetica, Arial, 文泉驛正黑, ''WenQuanYi Zen Hei'', ''Hiragino Sans GB'', ''儷黑 Pro'', ''LiHei Pro'', ''Heiti TC'', 微軟正黑體, ''Microsoft JhengHei UI'', ''Microsoft JhengHei'', sans-serif; font-size: 14px; background-color: #ffffff;"><div class="f7AU53" style="white-space-collapse: preserve; font-size: 0.875rem; overflow: hidden; text-overflow: ellipsis; line-height: 1.7;"><p class="irIKAp" style="margin: 0px;">❣❣ Welcome to our shop. I wish you a happy shopping experience! ❣❣</p><p class="irIKAp" style="margin: 0px;">&nbsp;</p><p class="irIKAp" style="margin: 0px;">➤ Shipping time:</p><p class="irIKAp" style="margin: 0px;">✤ 1-2 days Metro Manila</p><p class="irIKAp" style="margin: 0px;">✤ 3-4 days Provincial</p><p class="irIKAp" style="margin: 0px;">&nbsp;</p><p class="irIKAp" style="margin: 0px;">❤ Uses a larger 1.44-inch display, larger words, clearer, high-sensitivity touch, screen can achieve various operations, smart call reminder, Bluetooth camera, steps, calories, sleep monitoring Bluetooth music, heart rate quickly view various functions, and the touch operation of the large color screen is smoother, and smart life is started from then on.</p><p class="irIKAp" style="margin: 0px;">❤ The bracelet is made of diamond-shaped lattice texture. The TPU material has a skin-friendly texture and is soft and smooth. The single-breasted design allows you to relax and not fall off. The five colors are optional and can be replaced at will.</p><p class="irIKAp" style="margin: 0px;">❤ Plug-in design, plug-and-play and worry-free charging, the charging port is USB interface, remove the wrist strap, you can plug directly into the USB or charging head, easy and convenient.</p><p class="irIKAp" style="margin: 0px;">❤ Built-in optical heart rate sensor, pay attention to your heart rate changes 24 hours a day, record your heart rate changes in each time on the excellent living APP, outline your heart rate, present your health and beauty.</p><p class="irIKAp" style="margin: 0px;">❤ The APP connection product distinguishes the exercise warm-up, the burning fat, the cardio-pulmonary enhancement and the like by drawing a curve of the data obtained by the monitoring. Let you know the heart rate changes in each time period, reasonably plan the exercise intensity, and release your sports passion more effectively.</p><p class="irIKAp" style="margin: 0px;">&nbsp;</p><p class="irIKAp" style="margin: 0px;">➤ Item description:</p><p class="irIKAp" style="margin: 0px;">✤ Product name: 116plus smart bracelet</p><p class="irIKAp" style="margin: 0px;">✤ Screen size: 1.44inch</p><p class="irIKAp" style="margin: 0px;">✤ APP:Fitpro</p><p class="irIKAp" style="margin: 0px;">✤ Waterproof / Water-Resistant:Yes</p><p class="irIKAp" style="margin: 0px;">✤ Operating mode:Touch button</p><p class="irIKAp" style="margin: 0px;">✤ Compatible OS:Android,IOS</p><p class="irIKAp" style="margin: 0px;">✤ Shape of the dial:Square Shaped</p><p class="irIKAp" style="margin: 0px;">✤ Case material:Metal</p><p class="irIKAp" style="margin: 0px;">✤ Band material:Silicone</p><p class="irIKAp" style="margin: 0px;">✤ Package weight:0.043 kg</p><p class="irIKAp" style="margin: 0px;">✤ Package size (L x W x H):122X80X30mm</p><p class="irIKAp" style="margin: 0px;">&nbsp;</p><p class="irIKAp" style="margin: 0px;">➤ Packing list:</p><p class="irIKAp" style="margin: 0px;">✤ 116plus smart bracelet x1</p><p class="irIKAp" style="margin: 0px;">✤ User Manual x1</p><p class="irIKAp" style="margin: 0px;">&nbsp;</p><p class="irIKAp" style="margin: 0px;">☎ Note:</p><p class="irIKAp" style="margin: 0px;">✤ When leaving a FEEDBACK: Let us know your concern by contacting us before rating the received items. Give us a chance to make things right.</p><p class="irIKAp" style="margin: 0px;">✤ When CANCELLING an order: Be sure to leave a message on chat box stating the reason on cancellation.</p></div></div>', 23, N'Product Specifications Category Shopee Mobiles & Gadgets Wearables Smart Watches Warranty Type No Warranty Stock 554 Ships From Guiguinto, Bulacan', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, CAST(N'2023-11-07T08:50:00.000' AS DateTime), CAST(N'2023-11-21T08:50:00.000' AS DateTime), 1, 1000000, 50)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (440, N'iPhone 15 Pro Max', 996, 50000, N'440.jpg', NULL, 20, N'dadad', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-09-16' AS Date), 1, 0, NULL, NULL, CAST(N'2023-11-15T18:23:00.000' AS DateTime), CAST(N'2023-11-23T18:23:00.000' AS DateTime), 1, 40000, 20)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (441, N'Charger Original Fast Charger 3.1A Micro Usb/Data Line 2IN1 Adapter Set Android Charger a5s a12', 100, 69000, N'441.jpeg', N'<p><span style="color: rgba(0, 0, 0, 0.8); font-family: Roboto, ''Helvetica Neue'', Helvetica, Arial, 文泉驛正黑, ''WenQuanYi Zen Hei'', ''Hiragino Sans GB'', ''儷黑 Pro'', ''LiHei Pro'', ''Heiti TC'', 微軟正黑體, ''Microsoft JhengHei UI'', ''Microsoft JhengHei'', sans-serif; font-size: 14px; white-space-collapse: preserve; background-color: #ffffff;">Description: Product category: charging cable Connector B: Micro USB Maximum current: 3.1A Surface material: TPE Feature: 1. High-quality materials: high-quality copper cores provide safe and effective current transmission. Liquid silicone coating, soft to the touch and flexible to use. 2. Stable charging: the maximum current is 3A, it can charge 4500mAh battery in 90 minutes, and 5000mAh battery in 100 minutes. 3.Flexible: The softest, strong and durable material. The special stress relief design can withstand more than 10,000 bending tests. 4. USB-C / Micro USB connector (optional): This is a good charging option, used in the office/home/travel, and can quickly charge the device. 5. Compatibility: Universal, suitable for most Android smart phones with c/micro usb interface. note: Please select the correct interface (Micro USB) according to your mobile phone. Only use an adapter that supports fast charging to get the best fast charging effect. Due to different monitor and lighting effects, the actual color may be slightly different from the picture color. feature: Fashion color: simple and beautiful. SR connection: safe and wear-resistant, not easy to break. The real thing: high-quality thick copper core wire, lower resistance, faster and safer current transmission. #oppo #micro #micro usb #type c #type c cable fast charging #fast charging cable #fast charging charger #charging #charge #fast charging cable android #cable #usb cable #usb to usb cable #5v usb cable #OPPO charging line #2 in 1 #gift #cod #Android #oppo charger @oppo @oppo original fast charger</span></p>', 20, N'OPPO A57, OPPO F1s, OPPO R9s Plus, OPPO R9s, OPPO A37, OPPO A59, OPPO R9 Plus, OPPO F1Plus, OPPO F1, OPPO A53 etc.', N'bc8f3c03-8c07-4f28-bb67-0595ebdc17fd', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (442, N'Realme Charger 2A Data TYPE C/Micro USB 5/5i/6i C11/C12/C15 Compatible All ANDROID CABLE', 100, 13000, N'442.jpeg', N'<p><span style="color: rgba(0, 0, 0, 0.8); font-family: Roboto, ''Helvetica Neue'', Helvetica, Arial, 文泉驛正黑, ''WenQuanYi Zen Hei'', ''Hiragino Sans GB'', ''儷黑 Pro'', ''LiHei Pro'', ''Heiti TC'', 微軟正黑體, ''Microsoft JhengHei UI'', ''Microsoft JhengHei'', sans-serif; font-size: 14px; white-space-collapse: preserve; background-color: #ffffff;">Fast charging Easy to charge your equipment anytime anywhere, home, office, and traveling. Current charging 5V/2A VOOC Flash charging support Length: 100cm Color: Orange &amp; Black Packaging: Box Micro USB/ Type-C </span></p>', 20, N'Mobile Cable Type Micro USB, Type C Discount Stock 772 Other stocks 100088 Ships From Quiapo, Metro Manila', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-11-16' AS Date), 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Product] ([Productid], [Productname], [Soluong], [price], [image], [discription], [Categoryid], [sortdiscription], [Userid], [DateCreated], [status], [is_hot], [DiscountedPrice], [DiscountPercent], [DiscountStartTime], [DiscountEndTime], [Tinhtranggiamgia], [GiaGiamTheoKhungGio], [phantramgiamgia]) VALUES (445, N'Sang', 100, 5, N'=DISPIMG("ID_F7D6760E809E4500896D71E625874D07",1)', N'Bán Thân', 14, N'quá mệt mỏi', N'0279debd-bb14-4901-94ba-f03fb6eb2d6f', CAST(N'2023-11-18' AS Date), 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
INSERT [dbo].[ThanhToan] ([id], [TenHinhThuc]) VALUES (1, N'Thanh Toan Truc Tiep')
INSERT [dbo].[ThanhToan] ([id], [TenHinhThuc]) VALUES (2, N'MoMo')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [RoleNameIndex]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [RoleNameIndex] ON [dbo].[AspNetRoles]
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_UserId]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE NONCLUSTERED INDEX [IX_UserId] ON [dbo].[AspNetUserClaims]
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_UserId]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE NONCLUSTERED INDEX [IX_UserId] ON [dbo].[AspNetUserLogins]
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_RoleId]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE NONCLUSTERED INDEX [IX_RoleId] ON [dbo].[AspNetUserRoles]
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_UserId]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE NONCLUSTERED INDEX [IX_UserId] ON [dbo].[AspNetUserRoles]
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UserNameIndex]    Script Date: 11/24/2023 10:52:01 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UserNameIndex] ON [dbo].[AspNetUsers]
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AspNetUserClaims]  WITH CHECK ADD  CONSTRAINT [FK_dbo.AspNetUserClaims_dbo.AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AspNetUserClaims] CHECK CONSTRAINT [FK_dbo.AspNetUserClaims_dbo.AspNetUsers_UserId]
GO
ALTER TABLE [dbo].[AspNetUserLogins]  WITH CHECK ADD  CONSTRAINT [FK_dbo.AspNetUserLogins_dbo.AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AspNetUserLogins] CHECK CONSTRAINT [FK_dbo.AspNetUserLogins_dbo.AspNetUsers_UserId]
GO
ALTER TABLE [dbo].[AspNetUserRoles]  WITH CHECK ADD  CONSTRAINT [FK_dbo.AspNetUserRoles_dbo.AspNetRoles_RoleId] FOREIGN KEY([RoleId])
REFERENCES [dbo].[AspNetRoles] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AspNetUserRoles] CHECK CONSTRAINT [FK_dbo.AspNetUserRoles_dbo.AspNetRoles_RoleId]
GO
ALTER TABLE [dbo].[AspNetUserRoles]  WITH CHECK ADD  CONSTRAINT [FK_dbo.AspNetUserRoles_dbo.AspNetUsers_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[AspNetUsers] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AspNetUserRoles] CHECK CONSTRAINT [FK_dbo.AspNetUserRoles_dbo.AspNetUsers_UserId]
GO
ALTER TABLE [dbo].[CartItem]  WITH CHECK ADD  CONSTRAINT [FK_CartItem_Product] FOREIGN KEY([Productid])
REFERENCES [dbo].[Product] ([Productid])
GO
ALTER TABLE [dbo].[CartItem] CHECK CONSTRAINT [FK_CartItem_Product]
GO
ALTER TABLE [dbo].[extrafood]  WITH CHECK ADD  CONSTRAINT [FK__extrafood__Produ__33D4B598] FOREIGN KEY([Productid])
REFERENCES [dbo].[Product] ([Productid])
GO
ALTER TABLE [dbo].[extrafood] CHECK CONSTRAINT [FK__extrafood__Produ__33D4B598]
GO
ALTER TABLE [dbo].[Heartitem]  WITH CHECK ADD  CONSTRAINT [FK_Heartitem_Product] FOREIGN KEY([Productid])
REFERENCES [dbo].[Product] ([Productid])
GO
ALTER TABLE [dbo].[Heartitem] CHECK CONSTRAINT [FK_Heartitem_Product]
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK__Order_det__Od_id__300424B4] FOREIGN KEY([Od_id])
REFERENCES [dbo].[Orders] ([Od_id])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK__Order_det__Od_id__300424B4]
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK__Order_det__Produ__31EC6D26] FOREIGN KEY([Productid])
REFERENCES [dbo].[Product] ([Productid])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK__Order_det__Produ__31EC6D26]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_ThanhToan] FOREIGN KEY([idthanhtoan])
REFERENCES [dbo].[ThanhToan] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_ThanhToan]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([Categoryid])
REFERENCES [dbo].[Category] ([Categoryid])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
USE [master]
GO
ALTER DATABASE [Food_Web] SET  READ_WRITE 
GO
