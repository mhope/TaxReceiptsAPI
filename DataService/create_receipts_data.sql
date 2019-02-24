create table hmrc.receipts (
year_end year not null primary key,
total_paid_over int not null,
total_hmrc_receipts int not null,
paye int,
sa int,
capital_gains_tax int not null,
nics int not null,
vat int not null,
total_corporation_tax int not null,
offshore_corporation_tax int not null,
bank_levy int
);

insert into receipts values (2000, 291280, 294177, 93910, null, null, 2122, 56354, 56779, 34322, 1268, null);
insert into receipts values (2001, 309726, 315638, 105177, null, null, 3236, 60614, 58622, 32421, 2329, null);
insert into receipts values (2002, 314959, 321741, 107994, 91937, 15281, 3034, 63168, 61026, 32041, 3515, null);
insert into receipts values (2003, 317174, 324725, 109506, 94243, 16059, 1596, 64553, 63451, 29488, 3662, null);
insert into receipts values (2004, 331133, 347946, 117917, 101389, 15772, 2225, 72457, 69075, 28459, 3057, null);
insert into receipts values (2005, 355917, 375801, 127294, 108699, 17141, 2282, 78098, 73026, 34031, 3831, null);
insert into receipts values (2006, 382067, 402874, 134916, 113894, 18077, 3042, 85522, 72856, 42355, 7307, null);
insert into receipts values (2007, 406337, 428629, 147712, 124799, 20306, 3830, 87274, 77360, 44875, 6709, null);
insert into receipts values (2008, 431800, 456121, 151738, 126760, 22443, 5268, 100410, 80599, 47036, 5728, null);
insert into receipts values (2009, 416512, 445531, 153442, 128470, 22531, 7852, 96882, 78439, 43927, 9826, null);
insert into receipts values (2010, 382331, 414920, 144881, 122584, 21708, 2491, 95517, 70160, 36628, 4998, null);
insert into receipts values (2011, 419580, 453957, 153491, 132263, 22108, 3601, 96548, 83502, 43040, 6864, null);
insert into receipts values (2012, 437603, 472690, 150939, 132189, 20334, 4337, 101617, 98292, 43130, 8840, 1612);
insert into receipts values (2013, 437357, 474267, 152030, 132433, 20550, 3927, 102037, 100572, 40482, 4412, 1595);
insert into receipts values (2014, 456500, 494197, 156898, 134686, 20854, 3908, 107690, 104718, 40327, 3556, 2200);
insert into receipts values (2015, 476645, 515971, 163109, 139506, 23645, 5559, 110406, 111363, 43005, 2026, 2748);
insert into receipts values (2016, 494864, 534306, 168451, 145652, 24327, 7060, 113701, 115415, 44410, 560, 3392);
insert into receipts values (2017, 528677, 569394, 177065, 149751, 29293, 8561, 124469, 119799, 49534, 304, 2975);
insert into receipts values (2018, 557677, 593956, 180049, 154266, 28294, 7793, 130931, 125363, 54394, 1757, 2764);
