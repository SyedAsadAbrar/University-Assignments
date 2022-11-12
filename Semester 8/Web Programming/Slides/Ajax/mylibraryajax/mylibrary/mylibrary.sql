-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 29, 2020 at 06:09 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mylibrary`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
CREATE TABLE IF NOT EXISTS `authors` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`id`, `name`) VALUES
('9fa672beb6824a449ff08b4474aec1ac', 'Alexandre Dumas'),
('d6f2d47b92a94597be7e73cfa2ccef0d', 'Alphonse Daudet'),
('fc80cf5373ac434d80c4eef76c176804', 'Anna Sewell'),
('a98a78ee17cc4594b0f57446205761b5', 'Anne Brontë'),
('1f68d293a7884b45a089628d37de3926', 'Anthony Hope'),
('59f406d09c394a8e9a863a2d2283afc4', 'Anthony Trollope'),
('e5afb2571ce74a24a0effb15c1096607', 'Arthur Conan Doyle'),
('0031591af9604226ae57887d962ef7bf', 'Balzac'),
('f9e1afc0a76c44a88844a071be5eb21a', 'Bram Stoker'),
('0af09fbcf49b43cab70ec2ab9f5f17d7', 'Carlo Collodi'),
('273587998952423b98cc85b952736871', 'Charles Dickens'),
('9e9d4d383a924c798e2f5ea3c2b5948b', 'Charles Kingsley'),
('9c66d3a501ff497c950950a8bb667f62', 'Charlotte Brontë'),
('c9112dddc6f64e8b9dbf811e5dfd16f1', 'D.H. Lawrence'),
('82c41736d1c6441ca597aa338a20ba4d', 'Daniel Defoe'),
('7bf4f2d1832c4b708460c63dc3e17415', 'Edgar Allan Poe'),
('69affcc09a484c43ae074148c1fe9ccc', 'Edith Wharton'),
('66d6c5fa46bb4d909fe487e17e127f67', 'Elizabeth Gaskell'),
('cdfb89f56daf41e1b859374888a9dc65', 'Elizabeth George Speare'),
('e70be433950d46ce8d0c150c2a8a4ce9', 'Emily Brontë'),
('f237204d5f4846328e208fd6eaa6e65d', 'Flaubert'),
('882168c14f864823a4a6f6863451ea37', 'Frances Hodgson Burnett'),
('7f76eb6a5cae41fabf59fb38924e8dd2', 'Fyodor Dostoyevsky'),
('c3072754ddce497188f1081cc4e1fc51', 'G.K. Chesterton'),
('5139d587e0e94ea68e4f4022bcea9cda', 'Gaston Leroux'),
('763a306af96c41b1b7d9c2b53f00c2b1', 'George Eliot'),
('506d047635a54defb9eee8b099bf7b40', 'George Grossmith and Weedon Grossmith'),
('ca3138b2f90b4cb2a786c1092f9b71d3', 'George MacDonald'),
('29e123af875e445daa30d9e7bf3e9994', 'George Sand'),
('57c50458863f4160bad617269dd6ba31', 'H.G. Wells'),
('eb8cb5e5da6b4ccda503ee2f10a7ec69', 'Harriet Beecher Stowe'),
('393626700afb4340a8353789b73325f2', 'Henry David Thoreau'),
('9528ba709d7447079b67616aa69ce6e2', 'Henry James'),
('2a29e0f69e1c4d99b8492dfb4094247c', 'Henry Rider Haggard'),
('d6ef64dbdc584c15a6e18c552eafbf0f', 'Herman Melville'),
('48291fe8ac6141c9a3276aaca14143e4', 'Homer'),
('78d08d5a6b044236955f00dbf87bfba3', 'Jack London'),
('d6b16041d4f24d42bffc0dfa18abee24', 'James Fenimore Cooper'),
('33ae98e286414c93b0e80221fba4af14', 'Jane Austen'),
('5a7a2f04664a42fd87c8b921f2525687', 'John Bunyan'),
('a3e2a0a55382464583da26bcb3e4ad66', 'Jonathan Swift'),
('26b8e881f48e40ba9441b2adef4b3ae0', 'Joseph Conrad'),
('6acd9fffcc6c46e4987c751af4db9e80', 'Jules Verne'),
('3a1f5213cd184acd9ccc95da448ec47d', 'Kate Chopin'),
('325636dccb3b4d4484d931a2b7ae37c3', 'L. Frank Baum'),
('5ca50a0625214f18b4db8fc708fa2517', 'Laurence Sterne'),
('04e9f6b4b1834115b9626b5a67ea7bac', 'Leo Tolstoy'),
('7a231fdfb9f34068be6fac7b675e1ded', 'Lewis Carroll'),
('b3e9183167274942996470925feb45b3', 'Louisa May Alcott'),
('3eb243e42ebd44dfa731445a41f1523a', 'Mark Twain'),
('391f63ff3b79412291535c39c5df7870', 'Mary Shelley'),
('da9a22b69ec241c1869f5ff265e928e1', 'Maupassant'),
('341f31565ba24dbaab1bc9ed325e3144', 'Mérimée'),
('33629a0e43dd4f07a28043330a7f662b', 'Miguel de Cervantes Saavedra'),
('ec47571bc12d4d6a96547f4f78da7d6e', 'Nathaniel Hawthorne'),
('36a374a1ce00429d9123dfab0733257d', 'Niccolò Machiavelli'),
('1eb9b6696f4648abb27eeba4f7bba1a0', 'O. Henry'),
('3d253153d44c48ca8450b6ae8f9a5bd5', 'Oscar Wilde'),
('be3a080e5e134ec58472b9c4756572a0', 'Pierre Loti'),
('48b3a4da024f47148008fb61f3fa4a46', 'R D Blackmore'),
('6c0e0398ac4d4472afad0015074103ad', 'R. D. Blackmore'),
('d7676d6e34a044deb5110cbf11e5c3b6', 'Richard Francis Burton'),
('e441bac2d36143b587e16147d0c37be3', 'Rider Haggard'),
('c7629dc78e1b44bbad27bae91a886ff8', 'Robert Louis Stevenson'),
('667231b63c304fe29083f28c4cad59eb', 'Rudyard Kipling'),
('aed5c083f3ae43c3962aba7bc5a3b3e4', 'Sir Walter Scott'),
('c745043a9ec6400590ac73a0d9ac0491', 'Stephen Crane'),
('1d8e194f07fd4fd0a959a19247401a16', 'Susan Coolidge'),
('cbbafd2e3b4b4ee6a23cc4000e0405fe', 'Thomas Hardy'),
('36019738d4a0424797580d7430927664', 'Thomas More'),
('c68a5a6daddf4b77b00071177780b6dc', 'Thomas Paine'),
('e05bf43719b34b598b75372859fb6d75', 'Victor Hugo'),
('efd9b0f0a9c84727b0cf0cfbbf21c585', 'Voltaire'),
('f110d77b540746c2a2a66248be78c4e6', 'Washington Irving'),
('4e773a2bf4064d15b127f26809b16293', 'Wilkie Collins'),
('9d55d14014fc445f9393bb116cd67fea', 'William Makepeace Thackeray'),
('803b3e08af2a4fed85daa11c5058dd97', 'William Shakespeare'),
('ed71016f6ba444d2b5ea3551ecfb98b4', 'William Thackeray');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `id` varchar(32) NOT NULL,
  `title` varchar(200) NOT NULL,
  `isbn` varchar(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `isbn`) VALUES
('0968002fb8ac4701beb24c884219e036', 'Uncle Tom\'s Cabin', ''),
('fcee9aaddcc34615b38c7bcf984ff075', 'Lorna Doone', ''),
('e84d82685c0b4851bbe647ad46e09818', 'Sense and Sensibility', ''),
('1053441e7e124f46b80b6fd1e50cef3a', 'Persuasion', ''),
('589596a9ce6c41c9a078bdcb854ee229', 'Emma', ''),
('d2ac36a17760424eba4a8a5dd03c2cab', 'Mansfield Park', ''),
('0e672cd5111e46b59c6fbc03016e0d56', 'Pride and Prejudice', ''),
('1b9a80820f2d4e79b079290800d782d7', 'Little Women', ''),
('8b56c5a4da1a4e27aecadfcc2f761324', 'The Tenant of Wildfell Hall', ''),
('9352bddcb04f4473b13b93c03703f273', 'Jane Eyre', ''),
('28f186c3591240a492c13694f8a613ad', 'Shirley', ''),
('314b2ef443ee4c6389526d0bfea1bb70', 'Villette', ''),
('df7dbfb18b4a450da082c8ad56e232dd', 'The Professor', ''),
('1f8c49fb448a468b94867639fc2b4989', 'Wuthering Heights', ''),
('7896b99fe360423b820b4ef88ffc65f2', 'Pilgrim\'s Progress', ''),
('ef48a85ec1614499bca8b4805574d57d', 'The Secret Garden', ''),
('2b879f09da734b6688ea2e5f4d841d19', 'Little Lord Fauntleroy', ''),
('46ead9f6c47440ae81f21d2d8157e814', 'Alice\'s Adventures in Wonderland', ''),
('3611a73081b54bc99025f350d734d677', 'Through The Looking Glass', ''),
('23694903ad28406c98f65ed016004038', 'The Woman in White', ''),
('0eccc698ea944d90b1e067c4ec098a88', 'The Moonstone', ''),
('ea271d52774a4b0386e3b8a739e0a12f', 'The Adventures of Pinocchio', ''),
('b511e9c89e80426a8f5a227431338022', 'Lord Jim', ''),
('de55c1287ef94518a5fc1ccf5ed9a944', 'What Katy Did', ''),
('7dbcf319fb7245d19fd43647517ae488', 'Last of the Mohicans', ''),
('91b89437d90b474180f1c7784b27c347', 'Robinson Crusoe', ''),
('91ca8f56e1ca4f279a8721c0fa8820df', 'Adventures of Sherlock Holmes', ''),
('54a5b7cfef07423c93f5db3e88cea455', 'The Casebook of Sherlock Holmes', ''),
('ee6712f895c94a29874785346bfdb2ce', 'Bleak House', ''),
('001302de51914fc497e195a1467c0efd', 'Barnaby Rudge', ''),
('d7a0a3f0d36d43c68a55e6b6f1ef675a', 'A Christmas Carol', ''),
('3d908e4ee8cc491d87a31fe2c7ea0e72', 'David Copperfield', ''),
('e6941ee3acfd4f0db23785cbe9714628', 'Dombey and Son', ''),
('f842e16dad934a0f9db1660c26443a3d', 'Great Expectations', ''),
('412f940c7b214824a6b7cd2364d1e20d', 'Hard Times', ''),
('3b41c062008c4dd6ac91ceab93f56d32', 'Martin Chuzzlewit', ''),
('22f9a99df91246b785e0c0afc5399aa6', 'Nicholas Nickleby', ''),
('25158d3ae5b848ab87debf9c2c0b70c9', 'The Old Curiosity Shop', ''),
('660f3cb596ef4aa2b10d1db101613461', 'Oliver Twist', ''),
('d9a456e1eafd427e80ebe2dda12ddc1b', 'The Pickwick Papers', ''),
('7af289d4c4a240b58c2341c529f55572', 'A Tale of Two Cities', ''),
('f91efcc7dfbb473693508028fed51ce2', 'The Count of Monte Cristo', ''),
('269385f77d9c45d684ee9d348e6f2ade', 'The Three Musketeers', ''),
('fa22bb6455594123a0cd7292a9ba9a3f', 'Adam Bede', ''),
('099d2f98d0824b44865e83346ce7d53e', 'The Mill on the Floss', ''),
('bdff9c39292647b7baf5e8729600ab42', 'Middlemarch', ''),
('34d1c324e90c40d7960dffb602051a29', 'King Solomon\'s Mines', ''),
('9c106b47b79248118af755b0e654cfa1', 'Far From The Madding Crowd', ''),
('f6101b4662874aefbcc7e55208ddbda9', 'Under the Greenwood Tree', ''),
('f7a3930c3df8422f9e9f91eb21e8929e', 'Tess of the d\'Urbervilles', ''),
('a580d1854bf345a4b8309e4ba4cef90d', 'The Mayor Of Casterbridge', ''),
('04b70acdb2cd4837a944889e30d04414', 'The Scarlet Letter', ''),
('837c6aeed83e4d97b0480c1bf8aed7ca', 'Les Misérables', ''),
('c04e336231754312a4b1cae4309516eb', 'The Hunchback of Notre-Dame', ''),
('19f256aef8324d91956f2dda696b8c11', 'The Sketch Book of Geoffrey Crayon, Gent.', ''),
('2b17bc16027a4620bbdc69a2d3cb2619', 'Westward Ho!', ''),
('6886512764c6472e81400c204d632ee7', 'Sons and Lovers', ''),
('325d9d4b2aed45aba1a33208d14d069c', 'The Phantom Of The Opera', ''),
('46525535ff1844b3a9a0d67cdfb4efd8', 'The Call of the Wild', ''),
('eafaee760b324c53acee51e8184650c0', 'White Fang', ''),
('ae443898ea1e463599b2ae4a8bebf7e5', 'Moby Dick', ''),
('c45dd86fd6414715a5165f92ea263b37', 'Tales of Mystery and Imagination', ''),
('0a309d75c838466a9b8d4434919b8c62', 'Ivanhoe', ''),
('8d408391391e4d639dfc5adb726001f0', 'Rob Roy', ''),
('2243bbfc0a214b35ae8e930079b6b3bc', 'Waverley', ''),
('934b2a41ac0d407faf973877460f00f4', 'Black Beauty', ''),
('f8c9c1e7267a41e58716af8d3d18f7ce', 'All\'s Well That Ends Well', ''),
('b050c6bb0ba54c69b49a901acc47d4f2', 'Anthony and Cleopatra', ''),
('519866441bdf449e87dda13bd5dc2ff5', 'As You Like It', ''),
('fa425f8ad3e44b70ae7e236771742faa', 'The Comedy of Errors', ''),
('ae72fca54e614f779681f76458217822', 'Hamlet', ''),
('e722a89a2e6645fda5f04dff0168df99', 'Henry V', ''),
('4110ae90a05c4512981bf9ac28abfabf', 'Julius Caesar', ''),
('c504e97c3af9436d9d1b696f66df06f2', 'King Lear', ''),
('28dfeb4d78824be6afe73c831e95a146', 'Love\'s Labours Lost', ''),
('dfb545eafc6948d4a08e7a10c8d757b7', 'Macbeth', ''),
('0a51a2919df74641a4f487693298f890', 'The Merchant of Venice', ''),
('a0b949c20031452d955d582aa6594df9', 'A Midsummer Night\'s Dream', ''),
('7e50f5a48c8d4e5f8e1e925b580af7fa', 'Much Ado About Nothing', ''),
('6ec58ac5b8714a3c966daa3a1d6b7568', 'Othello', ''),
('8a9cfec916fe46a285a31baae28ba163', 'Richard III', ''),
('96077bfa6435493985fa62b0123d93fe', 'The Tempest', ''),
('1a3e6f00d7c24bc38509d1129302aecf', 'Timon of Athens', ''),
('c90cbebbb1c649debeb4440ab4db9655', 'Twelfth Night', ''),
('3147e86d7300477d88672307c429d2b9', 'The Winter\'s Tale', ''),
('85237436c5c6409a991858dab0693d66', 'Titus Andronicus', ''),
('d4c855ce644f44a690af4d55dab77492', 'The Taming Of The Shrew', ''),
('4d7596e457234a3f948bf818da9241b8', 'Romeo And Juliet', ''),
('1d83cf38836d41a787b89b030a49a08f', 'Treasure Island', ''),
('6b328910db7a48e7b4aca75dffda3a59', 'The Strange Case of Dr Jekyll and Mr Hyde', ''),
('ef79c4ce417644eeb16b53fe14fcf34c', 'Kidnapped', ''),
('5c1fbaca39b341e99630ed37e1307207', 'Gulliver\'s Travels', ''),
('91596a8d3747466e8a1ba09af019b51e', 'Vanity Fair', ''),
('2f8a88141f3346158b0eeded4ad6c039', 'Barchester Towers', ''),
('ab99de9eb4f9485a9e26559bd242ae8d', 'Adventures of Tom Sawyer', ''),
('11608b0f2a5a426baf1c3cbad472a47b', 'Adventures of Huckleberry Finn', ''),
('55fe4b0bcee7446aaf442a04b6b340b8', 'Around the World in Eighty Days', ''),
('5ccd3617e28848b88c1a3ede7ed1ce81', '20,000 Leagues Under the Sea', ''),
('ae9e546b1fa74cfdaf23e63796a572ef', 'The Picture of Dorian Gray', ''),
('0c9cb423c9ff41f7856c4b9c9a06899d', 'The Importance of Being Earnest', ''),
('02506a53a9f0417099891a864fe084d0', 'The Wonderful Wizard of Oz', ''),
('95b3ca064e514d13961e2411481b8278', 'Lorna Doone', ''),
('f744ff92eb504d50b8a15068eaaf3ec8', 'Tales from the Arabian Nights', ''),
('4da6e5b74d784253b5e229596d14fff1', 'Through the Looking-Glass', ''),
('b5e0faf782b6420d978897ddc3a3b034', 'Don Quixote of La Mancha', ''),
('8d22acfc588f44c1b4780593c6e52a1a', 'The Man Who Was Thursday', ''),
('6732f886ecc846adb6f83a14748d9c49', 'The Napoleon of Notting Hill', ''),
('33feb659e3264ea3925edeac992b2f00', 'The Awakening', ''),
('b0d045c4e277445980282fcffd692ff3', 'Heart of Darkness', ''),
('ead22b9f8e5a40398523de934b93ca7f', 'The Deerslayer', ''),
('f1591d8814a24b098d54da1a3c49a181', 'The Last of the Mohicans', ''),
('a2dbe795a4d343698dc90da7e1e7ef90', 'The Red Badge of Courage', ''),
('73ba09b9c821479ab1ca54956bdd2949', 'The Fortunes and Misfortunes of the Famous Moll Flanders', ''),
('80b6d3a6151b48aa875b66e2bd8793a2', 'The Brothers Karamazov', ''),
('79dde9e19ab54166a28025d0ef7996ea', 'Crime and Punishment', ''),
('af653aea7b4a41f18d7135de592c6436', 'The Adventures of Sherlock Holmes', ''),
('65bf52a9ccc14624a4d3bb7121be83a6', 'The Hound of the Baskervilles', ''),
('fbb243f6b1e54ebaa6a306b755b952bf', 'The Man in the Iron Mask', ''),
('4699975496ca4e888e237d8ef1a8c605', 'Silas Marner', ''),
('0e5befab1aa949be9f0e24f23289b3a1', 'The Diary of a Nobody', ''),
('f96d0661ef8e42edac4e82a68df4cb45', 'Allan Quatermain', ''),
('9c5c54c1d54f420db581d9b2da17524c', 'King Solomon\'s Mines', ''),
('94de5fb27eae4d30a2c70078aacae857', 'Far from the Madding Crowd', ''),
('2e9c63b6a609445ba1b07abcd5748467', 'Tess of the D\'Urbervilles', ''),
('0eb5f59474ec42fab3078fb34f5d13f8', 'Tanglewood Tales for Girls and Boys', ''),
('a9d9d82aa1e64d0ca175184ed87f406c', 'A Wonder-Book for Girls and Boys', ''),
('fd3702a71d0f4d3e87c3af33651c16d3', 'The Four Million', ''),
('952269a9bb9043668269b0cd083dcc21', 'The Odyssey', ''),
('79117ef8ac7649ddb44d3fecd2e760c5', 'The Prisoner of Zenda', ''),
('0c555d6585494fbcacf0f69f2f7539bb', 'The Aspern Papers', ''),
('ca5b30cc906242948e942e300621c56b', 'The Turn of the Screw', ''),
('a62f8dc33873437aa4e47c4602ba66b9', 'The Jungle Book', ''),
('969a4e15b4474f48afeeeb46b689c8be', 'Kim', ''),
('a6534023f30c4b3090fa452b0898ccc5', 'The Man Who Would Be King', ''),
('3fd9b920cc7847f48acae60b4d11b1fe', 'The Phantom of the Opera', ''),
('2d31e5fe6cbb449c81b7e60f0dbdc6cd', 'The Princess and Curdie', ''),
('a246fc1b65934fff95c50c2cb6701700', 'The Princess and the Goblin', ''),
('c30d1e4bd1104cf1abdabeb56cae5c15', 'The Prince', ''),
('b04b73282971424cb544b782321b3058', 'Utopia', ''),
('b9c25ca8428a4f8780f9348f0464fd03', 'Rights of Man', ''),
('cf6069b9c50448918c5e024a84bf75f3', 'Tales of Mystery & Imagination', ''),
('77b18190f01c4dd4b97c4dbab1b7f0ea', 'MacBeth', ''),
('1220b0143f164793bcfd38ef32ace99e', 'Othello, The Moor of Venice', ''),
('6a21c6a4851a4b038dfcd8c12f3f38e5', 'Romeo and Juliet', ''),
('20efa0d272a24aa5ac0cd986e37b6b42', 'The Taming of the Shrew', ''),
('b08b9e3849d946bca57822b0a098d417', 'Frankenstein', ''),
('8bec887f59274d9aa172dadc4de51207', 'The Strange Case of Dr. Jekyll and Mr. Hyde', ''),
('cc9c4214380448fa9abc6a3b22008aa7', 'Dracula', ''),
('b403f3ce19994528922b3a69c0a60e59', 'Vanity Fair', ''),
('78e40be0eae44ea09d082c5735126af5', 'Walden', ''),
('fe0234fc01c8412b85c897aa54dffd1e', 'Anna Karenina', ''),
('ddffbf3c0bc7449d8ebe6b94bc461e6e', 'War and Peace', ''),
('7926ab219fc444e2b91f02cad16cbb4f', 'The Adventures of Huckleberry Finn', ''),
('b8b649dc839d4d6095c7ac75f3698298', 'The Adventures of Tom Sawyer', ''),
('4ace4b5e958445edae807a42f516767d', 'A Connecticut Yankee in King Arthur\'s Court', ''),
('ad623c6fba594356870be5f76778feb6', 'Journey to the Center of the Earth', ''),
('2ff692da93f9451aa2f67a5ed49c896c', 'The Time Machine', ''),
('10274273d45840dabe8f71b45e6369c9', 'The Age of Innocence', ''),
('b2540cf920b8497a948dc57de53b1cca', 'Northanger Abbey', ''),
('e0a9ebf878974a058486af7be3dd3464', 'Agnes Grey', ''),
('3e9ba52e5faa4ff89be8f704da732aba', 'What Katy Did At School', ''),
('d65d49a18c0a4ca49b619afc2f086ce2', 'Cranford', ''),
('e86057fd40ec4e179cbd260e3360ab0c', 'The Water Babies', ''),
('8d91449cda884bdfa0b2b9420be39027', 'The Merry Wives of Windsor', ''),
('f6135aa9fb2a4f18864944035b6194d2', 'The Black Arrow', ''),
('8890cd82a98f4c80a32b52dbf98da090', 'The Secret Agent', ''),
('b64c418a7d864120a9203b16b623803e', 'Just So Stories', ''),
('5fc80e2e272c47b5b9f9233d1ecd0f54', 'The Life and Opinions of Tristram Shandy, Gentleman', ''),
('b8e31cd18d8f4d929ae792348fb0e4da', 'The Prince and the Pauper', ''),
('47fda3f89aea4896886c1473d45b1d87', 'The Happy Prince and Other Stories', ''),
('ebb1f1e15ff840459cb36d2fae2fe987', 'The Witch of Blackbird Pond', ''),
('0258905e5fc0438493ad1837db23339a', 'Eugénie Grandet', ''),
('f155670c65ff4dc48266dda3863a5b24', 'Le Petit Chose', ''),
('30d3e2dfa15b46e28e44c3785926263c', 'Trois contes', ''),
('99ef857037ed46f7b9f74b5abe0814af', 'Claude Gueux', ''),
('1851b739892d477f9a898f6f5cb3b375', 'Ramuntcho', ''),
('5b7af25cf7104263bf54fc6a0a8f32d4', 'Le Horla', ''),
('a0016f45318b4b2b925bf305c63e694d', 'La Vénus d\'Ille', ''),
('19a248737794414fbb2cb9d86deaa957', 'La Petite Fadette', ''),
('0cb8242ebedd479d9dfbf8f316a804cc', 'Les révoltés de la Bounty', ''),
('a85ca79defec4448897463947e231118', 'Micromégas', '');

-- --------------------------------------------------------

--
-- Table structure for table `books_authors`
--

DROP TABLE IF EXISTS `books_authors`;
CREATE TABLE IF NOT EXISTS `books_authors` (
  `book_id` varchar(32) NOT NULL,
  `author_id` varchar(32) NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books_authors`
--

INSERT INTO `books_authors` (`book_id`, `author_id`) VALUES
('001302de51914fc497e195a1467c0efd', '273587998952423b98cc85b952736871'),
('02506a53a9f0417099891a864fe084d0', '325636dccb3b4d4484d931a2b7ae37c3'),
('0258905e5fc0438493ad1837db23339a', '0031591af9604226ae57887d962ef7bf'),
('04b70acdb2cd4837a944889e30d04414', 'ec47571bc12d4d6a96547f4f78da7d6e'),
('0968002fb8ac4701beb24c884219e036', 'eb8cb5e5da6b4ccda503ee2f10a7ec69'),
('099d2f98d0824b44865e83346ce7d53e', '763a306af96c41b1b7d9c2b53f00c2b1'),
('0a309d75c838466a9b8d4434919b8c62', 'aed5c083f3ae43c3962aba7bc5a3b3e4'),
('0a51a2919df74641a4f487693298f890', '803b3e08af2a4fed85daa11c5058dd97'),
('0c555d6585494fbcacf0f69f2f7539bb', '9528ba709d7447079b67616aa69ce6e2'),
('0c9cb423c9ff41f7856c4b9c9a06899d', '3d253153d44c48ca8450b6ae8f9a5bd5'),
('0cb8242ebedd479d9dfbf8f316a804cc', '6acd9fffcc6c46e4987c751af4db9e80'),
('0e5befab1aa949be9f0e24f23289b3a1', '506d047635a54defb9eee8b099bf7b40'),
('0e672cd5111e46b59c6fbc03016e0d56', '33ae98e286414c93b0e80221fba4af14'),
('0eb5f59474ec42fab3078fb34f5d13f8', 'ec47571bc12d4d6a96547f4f78da7d6e'),
('0eccc698ea944d90b1e067c4ec098a88', '4e773a2bf4064d15b127f26809b16293'),
('10274273d45840dabe8f71b45e6369c9', '69affcc09a484c43ae074148c1fe9ccc'),
('1053441e7e124f46b80b6fd1e50cef3a', '33ae98e286414c93b0e80221fba4af14'),
('11608b0f2a5a426baf1c3cbad472a47b', '3eb243e42ebd44dfa731445a41f1523a'),
('1220b0143f164793bcfd38ef32ace99e', '803b3e08af2a4fed85daa11c5058dd97'),
('1851b739892d477f9a898f6f5cb3b375', 'be3a080e5e134ec58472b9c4756572a0'),
('19a248737794414fbb2cb9d86deaa957', '29e123af875e445daa30d9e7bf3e9994'),
('19f256aef8324d91956f2dda696b8c11', 'f110d77b540746c2a2a66248be78c4e6'),
('1a3e6f00d7c24bc38509d1129302aecf', '803b3e08af2a4fed85daa11c5058dd97'),
('1b9a80820f2d4e79b079290800d782d7', 'b3e9183167274942996470925feb45b3'),
('1d83cf38836d41a787b89b030a49a08f', 'c7629dc78e1b44bbad27bae91a886ff8'),
('1f8c49fb448a468b94867639fc2b4989', 'e70be433950d46ce8d0c150c2a8a4ce9'),
('20efa0d272a24aa5ac0cd986e37b6b42', '803b3e08af2a4fed85daa11c5058dd97'),
('2243bbfc0a214b35ae8e930079b6b3bc', 'aed5c083f3ae43c3962aba7bc5a3b3e4'),
('22f9a99df91246b785e0c0afc5399aa6', '273587998952423b98cc85b952736871'),
('23694903ad28406c98f65ed016004038', '4e773a2bf4064d15b127f26809b16293'),
('25158d3ae5b848ab87debf9c2c0b70c9', '273587998952423b98cc85b952736871'),
('269385f77d9c45d684ee9d348e6f2ade', '9fa672beb6824a449ff08b4474aec1ac'),
('28dfeb4d78824be6afe73c831e95a146', '803b3e08af2a4fed85daa11c5058dd97'),
('28f186c3591240a492c13694f8a613ad', '9c66d3a501ff497c950950a8bb667f62'),
('2b17bc16027a4620bbdc69a2d3cb2619', '9e9d4d383a924c798e2f5ea3c2b5948b'),
('2b879f09da734b6688ea2e5f4d841d19', '882168c14f864823a4a6f6863451ea37'),
('2d31e5fe6cbb449c81b7e60f0dbdc6cd', 'ca3138b2f90b4cb2a786c1092f9b71d3'),
('2e9c63b6a609445ba1b07abcd5748467', 'cbbafd2e3b4b4ee6a23cc4000e0405fe'),
('2f8a88141f3346158b0eeded4ad6c039', '59f406d09c394a8e9a863a2d2283afc4'),
('2ff692da93f9451aa2f67a5ed49c896c', '57c50458863f4160bad617269dd6ba31'),
('30d3e2dfa15b46e28e44c3785926263c', 'f237204d5f4846328e208fd6eaa6e65d'),
('3147e86d7300477d88672307c429d2b9', '803b3e08af2a4fed85daa11c5058dd97'),
('314b2ef443ee4c6389526d0bfea1bb70', '9c66d3a501ff497c950950a8bb667f62'),
('325d9d4b2aed45aba1a33208d14d069c', '5139d587e0e94ea68e4f4022bcea9cda'),
('33feb659e3264ea3925edeac992b2f00', '3a1f5213cd184acd9ccc95da448ec47d'),
('34d1c324e90c40d7960dffb602051a29', 'e441bac2d36143b587e16147d0c37be3'),
('3611a73081b54bc99025f350d734d677', '7a231fdfb9f34068be6fac7b675e1ded'),
('3b41c062008c4dd6ac91ceab93f56d32', '273587998952423b98cc85b952736871'),
('3d908e4ee8cc491d87a31fe2c7ea0e72', '273587998952423b98cc85b952736871'),
('3e9ba52e5faa4ff89be8f704da732aba', '1d8e194f07fd4fd0a959a19247401a16'),
('3fd9b920cc7847f48acae60b4d11b1fe', '5139d587e0e94ea68e4f4022bcea9cda'),
('4110ae90a05c4512981bf9ac28abfabf', '803b3e08af2a4fed85daa11c5058dd97'),
('412f940c7b214824a6b7cd2364d1e20d', '273587998952423b98cc85b952736871'),
('46525535ff1844b3a9a0d67cdfb4efd8', '78d08d5a6b044236955f00dbf87bfba3'),
('4699975496ca4e888e237d8ef1a8c605', '763a306af96c41b1b7d9c2b53f00c2b1'),
('46ead9f6c47440ae81f21d2d8157e814', '7a231fdfb9f34068be6fac7b675e1ded'),
('47fda3f89aea4896886c1473d45b1d87', '3d253153d44c48ca8450b6ae8f9a5bd5'),
('4ace4b5e958445edae807a42f516767d', '3eb243e42ebd44dfa731445a41f1523a'),
('4d7596e457234a3f948bf818da9241b8', '803b3e08af2a4fed85daa11c5058dd97'),
('4da6e5b74d784253b5e229596d14fff1', '7a231fdfb9f34068be6fac7b675e1ded'),
('519866441bdf449e87dda13bd5dc2ff5', '803b3e08af2a4fed85daa11c5058dd97'),
('54a5b7cfef07423c93f5db3e88cea455', 'e5afb2571ce74a24a0effb15c1096607'),
('55fe4b0bcee7446aaf442a04b6b340b8', '6acd9fffcc6c46e4987c751af4db9e80'),
('589596a9ce6c41c9a078bdcb854ee229', '33ae98e286414c93b0e80221fba4af14'),
('5b7af25cf7104263bf54fc6a0a8f32d4', 'da9a22b69ec241c1869f5ff265e928e1'),
('5c1fbaca39b341e99630ed37e1307207', 'a3e2a0a55382464583da26bcb3e4ad66'),
('5ccd3617e28848b88c1a3ede7ed1ce81', '6acd9fffcc6c46e4987c751af4db9e80'),
('5fc80e2e272c47b5b9f9233d1ecd0f54', '5ca50a0625214f18b4db8fc708fa2517'),
('65bf52a9ccc14624a4d3bb7121be83a6', 'e5afb2571ce74a24a0effb15c1096607'),
('660f3cb596ef4aa2b10d1db101613461', '273587998952423b98cc85b952736871'),
('6732f886ecc846adb6f83a14748d9c49', 'c3072754ddce497188f1081cc4e1fc51'),
('6886512764c6472e81400c204d632ee7', 'c9112dddc6f64e8b9dbf811e5dfd16f1'),
('6a21c6a4851a4b038dfcd8c12f3f38e5', '803b3e08af2a4fed85daa11c5058dd97'),
('6b328910db7a48e7b4aca75dffda3a59', 'c7629dc78e1b44bbad27bae91a886ff8'),
('6ec58ac5b8714a3c966daa3a1d6b7568', '803b3e08af2a4fed85daa11c5058dd97'),
('73ba09b9c821479ab1ca54956bdd2949', '82c41736d1c6441ca597aa338a20ba4d'),
('77b18190f01c4dd4b97c4dbab1b7f0ea', '803b3e08af2a4fed85daa11c5058dd97'),
('7896b99fe360423b820b4ef88ffc65f2', '5a7a2f04664a42fd87c8b921f2525687'),
('78e40be0eae44ea09d082c5735126af5', '393626700afb4340a8353789b73325f2'),
('79117ef8ac7649ddb44d3fecd2e760c5', '1f68d293a7884b45a089628d37de3926'),
('7926ab219fc444e2b91f02cad16cbb4f', '3eb243e42ebd44dfa731445a41f1523a'),
('79dde9e19ab54166a28025d0ef7996ea', '7f76eb6a5cae41fabf59fb38924e8dd2'),
('7af289d4c4a240b58c2341c529f55572', '273587998952423b98cc85b952736871'),
('7dbcf319fb7245d19fd43647517ae488', 'd6b16041d4f24d42bffc0dfa18abee24'),
('7e50f5a48c8d4e5f8e1e925b580af7fa', '803b3e08af2a4fed85daa11c5058dd97'),
('80b6d3a6151b48aa875b66e2bd8793a2', '7f76eb6a5cae41fabf59fb38924e8dd2'),
('837c6aeed83e4d97b0480c1bf8aed7ca', 'e05bf43719b34b598b75372859fb6d75'),
('85237436c5c6409a991858dab0693d66', '803b3e08af2a4fed85daa11c5058dd97'),
('8890cd82a98f4c80a32b52dbf98da090', '26b8e881f48e40ba9441b2adef4b3ae0'),
('8a9cfec916fe46a285a31baae28ba163', '803b3e08af2a4fed85daa11c5058dd97'),
('8b56c5a4da1a4e27aecadfcc2f761324', 'a98a78ee17cc4594b0f57446205761b5'),
('8bec887f59274d9aa172dadc4de51207', 'c7629dc78e1b44bbad27bae91a886ff8'),
('8d22acfc588f44c1b4780593c6e52a1a', 'c3072754ddce497188f1081cc4e1fc51'),
('8d408391391e4d639dfc5adb726001f0', 'aed5c083f3ae43c3962aba7bc5a3b3e4'),
('8d91449cda884bdfa0b2b9420be39027', '803b3e08af2a4fed85daa11c5058dd97'),
('91596a8d3747466e8a1ba09af019b51e', 'ed71016f6ba444d2b5ea3551ecfb98b4'),
('91b89437d90b474180f1c7784b27c347', '82c41736d1c6441ca597aa338a20ba4d'),
('91ca8f56e1ca4f279a8721c0fa8820df', 'e5afb2571ce74a24a0effb15c1096607'),
('934b2a41ac0d407faf973877460f00f4', 'fc80cf5373ac434d80c4eef76c176804'),
('9352bddcb04f4473b13b93c03703f273', '9c66d3a501ff497c950950a8bb667f62'),
('94de5fb27eae4d30a2c70078aacae857', 'cbbafd2e3b4b4ee6a23cc4000e0405fe'),
('952269a9bb9043668269b0cd083dcc21', '48291fe8ac6141c9a3276aaca14143e4'),
('95b3ca064e514d13961e2411481b8278', '6c0e0398ac4d4472afad0015074103ad'),
('96077bfa6435493985fa62b0123d93fe', '803b3e08af2a4fed85daa11c5058dd97'),
('969a4e15b4474f48afeeeb46b689c8be', '667231b63c304fe29083f28c4cad59eb'),
('99ef857037ed46f7b9f74b5abe0814af', 'e05bf43719b34b598b75372859fb6d75'),
('9c106b47b79248118af755b0e654cfa1', 'cbbafd2e3b4b4ee6a23cc4000e0405fe'),
('9c5c54c1d54f420db581d9b2da17524c', '2a29e0f69e1c4d99b8492dfb4094247c'),
('a0016f45318b4b2b925bf305c63e694d', '341f31565ba24dbaab1bc9ed325e3144'),
('a0b949c20031452d955d582aa6594df9', '803b3e08af2a4fed85daa11c5058dd97'),
('a246fc1b65934fff95c50c2cb6701700', 'ca3138b2f90b4cb2a786c1092f9b71d3'),
('a2dbe795a4d343698dc90da7e1e7ef90', 'c745043a9ec6400590ac73a0d9ac0491'),
('a580d1854bf345a4b8309e4ba4cef90d', 'cbbafd2e3b4b4ee6a23cc4000e0405fe'),
('a62f8dc33873437aa4e47c4602ba66b9', '667231b63c304fe29083f28c4cad59eb'),
('a6534023f30c4b3090fa452b0898ccc5', '667231b63c304fe29083f28c4cad59eb'),
('a85ca79defec4448897463947e231118', 'efd9b0f0a9c84727b0cf0cfbbf21c585'),
('a9d9d82aa1e64d0ca175184ed87f406c', 'ec47571bc12d4d6a96547f4f78da7d6e'),
('ab99de9eb4f9485a9e26559bd242ae8d', '3eb243e42ebd44dfa731445a41f1523a'),
('ad623c6fba594356870be5f76778feb6', '6acd9fffcc6c46e4987c751af4db9e80'),
('ae443898ea1e463599b2ae4a8bebf7e5', 'd6ef64dbdc584c15a6e18c552eafbf0f'),
('ae72fca54e614f779681f76458217822', '803b3e08af2a4fed85daa11c5058dd97'),
('ae9e546b1fa74cfdaf23e63796a572ef', '3d253153d44c48ca8450b6ae8f9a5bd5'),
('af653aea7b4a41f18d7135de592c6436', 'e5afb2571ce74a24a0effb15c1096607'),
('b04b73282971424cb544b782321b3058', '36019738d4a0424797580d7430927664'),
('b050c6bb0ba54c69b49a901acc47d4f2', '803b3e08af2a4fed85daa11c5058dd97'),
('b08b9e3849d946bca57822b0a098d417', '391f63ff3b79412291535c39c5df7870'),
('b0d045c4e277445980282fcffd692ff3', '26b8e881f48e40ba9441b2adef4b3ae0'),
('b2540cf920b8497a948dc57de53b1cca', '33ae98e286414c93b0e80221fba4af14'),
('b403f3ce19994528922b3a69c0a60e59', '9d55d14014fc445f9393bb116cd67fea'),
('b511e9c89e80426a8f5a227431338022', '26b8e881f48e40ba9441b2adef4b3ae0'),
('b5e0faf782b6420d978897ddc3a3b034', '33629a0e43dd4f07a28043330a7f662b'),
('b64c418a7d864120a9203b16b623803e', '667231b63c304fe29083f28c4cad59eb'),
('b8b649dc839d4d6095c7ac75f3698298', '3eb243e42ebd44dfa731445a41f1523a'),
('b8e31cd18d8f4d929ae792348fb0e4da', '3eb243e42ebd44dfa731445a41f1523a'),
('b9c25ca8428a4f8780f9348f0464fd03', 'c68a5a6daddf4b77b00071177780b6dc'),
('bdff9c39292647b7baf5e8729600ab42', '763a306af96c41b1b7d9c2b53f00c2b1'),
('c04e336231754312a4b1cae4309516eb', 'e05bf43719b34b598b75372859fb6d75'),
('c30d1e4bd1104cf1abdabeb56cae5c15', '36a374a1ce00429d9123dfab0733257d'),
('c45dd86fd6414715a5165f92ea263b37', '7bf4f2d1832c4b708460c63dc3e17415'),
('c504e97c3af9436d9d1b696f66df06f2', '803b3e08af2a4fed85daa11c5058dd97'),
('c90cbebbb1c649debeb4440ab4db9655', '803b3e08af2a4fed85daa11c5058dd97'),
('ca5b30cc906242948e942e300621c56b', '9528ba709d7447079b67616aa69ce6e2'),
('cc9c4214380448fa9abc6a3b22008aa7', 'f9e1afc0a76c44a88844a071be5eb21a'),
('cf6069b9c50448918c5e024a84bf75f3', '7bf4f2d1832c4b708460c63dc3e17415'),
('d2ac36a17760424eba4a8a5dd03c2cab', '33ae98e286414c93b0e80221fba4af14'),
('d4c855ce644f44a690af4d55dab77492', '803b3e08af2a4fed85daa11c5058dd97'),
('d65d49a18c0a4ca49b619afc2f086ce2', '66d6c5fa46bb4d909fe487e17e127f67'),
('d7a0a3f0d36d43c68a55e6b6f1ef675a', '273587998952423b98cc85b952736871'),
('d9a456e1eafd427e80ebe2dda12ddc1b', '273587998952423b98cc85b952736871'),
('ddffbf3c0bc7449d8ebe6b94bc461e6e', '04e9f6b4b1834115b9626b5a67ea7bac'),
('de55c1287ef94518a5fc1ccf5ed9a944', '1d8e194f07fd4fd0a959a19247401a16'),
('df7dbfb18b4a450da082c8ad56e232dd', '9c66d3a501ff497c950950a8bb667f62'),
('dfb545eafc6948d4a08e7a10c8d757b7', '803b3e08af2a4fed85daa11c5058dd97'),
('e0a9ebf878974a058486af7be3dd3464', 'a98a78ee17cc4594b0f57446205761b5'),
('e6941ee3acfd4f0db23785cbe9714628', '273587998952423b98cc85b952736871'),
('e722a89a2e6645fda5f04dff0168df99', '803b3e08af2a4fed85daa11c5058dd97'),
('e84d82685c0b4851bbe647ad46e09818', '33ae98e286414c93b0e80221fba4af14'),
('e86057fd40ec4e179cbd260e3360ab0c', '9e9d4d383a924c798e2f5ea3c2b5948b'),
('ea271d52774a4b0386e3b8a739e0a12f', '0af09fbcf49b43cab70ec2ab9f5f17d7'),
('ead22b9f8e5a40398523de934b93ca7f', 'd6b16041d4f24d42bffc0dfa18abee24'),
('eafaee760b324c53acee51e8184650c0', '78d08d5a6b044236955f00dbf87bfba3'),
('ebb1f1e15ff840459cb36d2fae2fe987', 'cdfb89f56daf41e1b859374888a9dc65'),
('ee6712f895c94a29874785346bfdb2ce', '273587998952423b98cc85b952736871'),
('ef48a85ec1614499bca8b4805574d57d', '882168c14f864823a4a6f6863451ea37'),
('ef79c4ce417644eeb16b53fe14fcf34c', 'c7629dc78e1b44bbad27bae91a886ff8'),
('f155670c65ff4dc48266dda3863a5b24', 'd6f2d47b92a94597be7e73cfa2ccef0d'),
('f1591d8814a24b098d54da1a3c49a181', 'd6b16041d4f24d42bffc0dfa18abee24'),
('f6101b4662874aefbcc7e55208ddbda9', 'cbbafd2e3b4b4ee6a23cc4000e0405fe'),
('f6135aa9fb2a4f18864944035b6194d2', 'c7629dc78e1b44bbad27bae91a886ff8'),
('f744ff92eb504d50b8a15068eaaf3ec8', 'd7676d6e34a044deb5110cbf11e5c3b6'),
('f7a3930c3df8422f9e9f91eb21e8929e', 'cbbafd2e3b4b4ee6a23cc4000e0405fe'),
('f842e16dad934a0f9db1660c26443a3d', '273587998952423b98cc85b952736871'),
('f8c9c1e7267a41e58716af8d3d18f7ce', '803b3e08af2a4fed85daa11c5058dd97'),
('f91efcc7dfbb473693508028fed51ce2', '9fa672beb6824a449ff08b4474aec1ac'),
('f96d0661ef8e42edac4e82a68df4cb45', '2a29e0f69e1c4d99b8492dfb4094247c'),
('fa22bb6455594123a0cd7292a9ba9a3f', '763a306af96c41b1b7d9c2b53f00c2b1'),
('fa425f8ad3e44b70ae7e236771742faa', '803b3e08af2a4fed85daa11c5058dd97'),
('fbb243f6b1e54ebaa6a306b755b952bf', '9fa672beb6824a449ff08b4474aec1ac'),
('fcee9aaddcc34615b38c7bcf984ff075', '48b3a4da024f47148008fb61f3fa4a46'),
('fd3702a71d0f4d3e87c3af33651c16d3', '1eb9b6696f4648abb27eeba4f7bba1a0'),
('fe0234fc01c8412b85c897aa54dffd1e', '04e9f6b4b1834115b9626b5a67ea7bac');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `password`) VALUES
('64529109c4154e80b11eb0ce03b2ea24', 'Administrator', 'admin', 'admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;