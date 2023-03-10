package nro.main;

import java.io.IOException;
import java.sql.SQLException;

import nro.giftcode.GiftCodeManager;
import nro.io.Message;
import nro.io.Session;
import nro.item.Item;
import nro.item.ItemOption;
import nro.item.ItemSell;
import nro.item.ItemService;
import nro.player.Boss;
import nro.shop.Shop;
import nro.shop.TabItemShop;
import nro.skill.NoiTai;
import nro.skill.NoiTaiTemplate;
import nro.map.Map;
import nro.clan.ClanService;
import nro.clan.Member;
import nro.player.Player;
import nro.player.Detu;
import nro.player.PlayerManger;
import nro.task.TaskService;
import nro.task.TaskManager;
import nro.daihoi.DaiHoiService;
import nro.daihoi.DaiHoiManager;
import nro.item.ItemBuff;

public class Menu {

    Server server = Server.gI();

    public static void doMenuArray(Player p, int idnpc, String chat, String[] menu) {
        Message m = null;
        try {
            m = new Message(32);
            m.writer().writeShort(idnpc);
            m.writer().writeUTF(chat);
            m.writer().writeByte(menu.length);
            for (byte i = 0; i < menu.length; ++i) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public static void doMenuArraySay(Player p, short id, String[] menu) {
        Message m = null;
        try {
            m = new Message(38);
            m.writer().writeShort(id);
            for (byte i = 0; i < menu.length; i++) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public static void sendWrite(Player p, String title, short type) {
        Message m = null;
        try {
            m = new Message(88);
            m.writer().writeUTF(title);
            m.writer().writeShort(type);
            m.writer().flush();
            p.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void textBoxId(Session session, short menuId, String str) {
        Message msg;
        try {
            msg = new Message(88);
            msg.writer().writeInt(menuId);
            msg.writer().writeUTF(str);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendTB(Session session, Player title, String s) {
        Message m = null;
        try {
            m = new Message(94);
            m.writer().writeUTF(title.name);
            m.writer().writeUTF(s);
            m.writer().flush();
            PlayerManger.gI().SendMessageServer(m);
            session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void ChatTG(Player p, int avatar, String chat3, byte cmd) {
        Message m = null;
        try {
            m = new Message(-70);
            m.writer().writeShort(avatar);
            m.writer().writeUTF(chat3);
            m.writer().writeByte(cmd);
            m.writer().flush();
            PlayerManger.gI().SendMessageServer(m);
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void ChatTG(Player p, short avatar, String str) {
        Message m = null;
        try {
            m = new Message(94);
            m.writer().writeShort(avatar);
            m.writer().writeUTF(str);
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void LuckyRound(Player p, byte type, byte soluong) throws IOException {
        Message m = null;
        try {
            if (type == 0) {
                m = new Message(-127);
                m.writer().writeByte(type);
                short[] arId = new short[]{2280, 2281, 2282, 2283, 2284, 2285, 2286};
                m.writer().writeByte(7);
                for (short i = 0; i < arId.length; i++) {
                    m.writer().writeShort(arId[i]);
                }
                m.writer().writeByte(soluong);
                m.writer().writeInt(10000);
                m.writer().writeShort(0);
                m.writer().flush();
                p.session.sendMessage(m);
            } else if (type == 1) {
                m = new Message(-127);
                m.writer().writeByte(soluong);
                short[] arId = new short[]{2, 3, 4, 5, 6, 7, 8};
                for (short i = 0; i < soluong; i++) {
                    m.writer().writeShort(arId[i]);
                }
                m.writer().flush();
                p.session.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void confirmMenu(Player p, Message m) throws IOException, SQLException, InterruptedException {
        short idNpc = m.reader().readShort();
        byte select = m.reader().readByte();
//        Util.log("ID NPC: " + idNpc);
//        Util.log("SELECT: " + select);
//        Util.log("p.menuID: " + p.menuID);
        switch (p.menuNPCID) {
            case 999: {
                if (p.id == 1 && p.name.equals("admin") || p.name.equals("nhtgame") || p.name.equals("berus") || p.name.equals("coldxayda")) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            //p.sendAddchatYellow("Chu??c b???n ch??i game vui v??? kh??ng qu???o!");
                            doMenuArray(p, idNpc, "LIST BOSS", new String[]{"Broly", "Super Broly", "Cooler", "Cooler 2", "Black",
                                "Super Black", "Kuku", "M???p ??inh", "Rambo", "S??? 4", "S??? 3", "S??? 2", "S??? 1", "Ti???u ?????i\nTr?????ng", "Fide 1", "Fide 2", "Fide 3",
                                "Android 19", "Dr.K??r??", "Android 15", "Android 14", "Android 13", "Poc", "Pic", "KingKong", "X??n 1", "X??n 2", "X??n Ho??n\nThi???n",
                                "X??n Con", "Si??u B???\nHung", "Trung ??y\nTr???ng", "Trung ??y\nXanh L??", "Trung ??y\nTh??p", "Ninja\n??o T??m", "Rob???t\nV??? S??",
                                "Drabura", "Pui Pui", "Ya C??n", "Ma B??", "Chilled", "Chilled 2", "Dr Lychee", "Hatchiyack", "T???p s???", "T??n binh", "Chi???n binh",
                                "?????i tr?????ng", "Zamasu", "Bill", "Whis", "S??n Tinh", "Thu??? Tinh", "Rati", "Fide V??ng", "??n Tr???m", "Vegito\nQuy???n N??ng", "Super\nVegito", "Piccolo\nT???i Th?????ng "});
                        } else if (select == 1) {
                            m = null;
                            try {
                                m = new Message(-125);
                                m.writer().writeUTF("Buff Item To Player");
                                m.writer().writeByte((byte) 3);
                                m.writer().writeUTF("Name Player");
                                m.writer().writeByte((byte) 1);
                                m.writer().writeUTF("ID Item");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("S??? l?????ng");
                                m.writer().writeByte((byte) 0);
                                m.writer().flush();
                                p.session.sendMessage(m);
                                m.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (m != null) {
                                    m.cleanup();
                                }
                            }
                        } else if (select == 2) {
                            Service.gI().clientInput(p, "Nh???p giftcode", "Giftcode", (byte) 0);
                            //GiftCodeManager.gI().checkInfomationGiftCode(p);
                        }else if (select == 3){
                        m = null;
                            try {
                                m = new Message(-125);
                                m.writer().writeUTF("NHTGAME Buff V???t Ph???m Option");
                                m.writer().writeByte((byte) 5);
                                m.writer().writeUTF("T??n Ng?????i Ch??i");
                                m.writer().writeByte((byte) 1);
                                m.writer().writeUTF("ID Trang B???");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("ID Option");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("Param");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("S??? L?????ng");
                                m.writer().writeByte((byte) 0);
                                m.writer().flush();
                                p.session.sendMessage(m);
                                m.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (m != null) {
                                    m.cleanup();
                                }
                            }
                        } else if (select == 4) {
                            m = null;
                            try {
                                m = new Message(-125);
                                m.writer().writeUTF("NHTGAME Buff SKH Option V2");
                                m.writer().writeByte((byte) 6);
                                m.writer().writeUTF("T??n Ng?????i Ch??i");
                                m.writer().writeByte((byte) 1);
                                m.writer().writeUTF("ID Trang B???");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("ID Option SKH 127 > 135");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("ID Option Bonus");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("Param");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("S??? L?????ng");
                                m.writer().writeByte((byte) 0);
                                m.writer().flush();
                                p.session.sendMessage(m);
                                m.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (m != null) {
                                    m.cleanup();
                                }
                            }
                        } else if (select == 5) {
                            m = null;
                            try {
                                m = new Message(-125);
                                m.writer().writeUTF("NHTGAME BUFF KunCoin Player");
                                m.writer().writeByte((byte) 2);
                                m.writer().writeUTF("T??n Ng?????i Ch??i");
                                m.writer().writeByte((byte) 1);
                                m.writer().writeUTF("S??? L?????ng");
                                m.writer().writeByte((byte) 0);
                                m.writer().flush();
                                p.session.sendMessage(m);
                                m.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (m != null) {
                                    m.cleanup();
                                }
                            }
                        
                        
                        }
                        p.menuID = select;
                        break;
                    } else if (p.menuID == 0) {
                        byte typeBoss = (byte) (select + 1);
                        Boss _bossCall = new Boss(Server.gI().idBossCall, typeBoss, p.x, p.y);
                        if (typeBoss == (byte) 2) {
                            Detu _rDetu = new Detu();
                            _rDetu.initDetuBroly(_rDetu);
                            _rDetu.id = -300000 - Server.gI().idBossCall;
                            _rDetu.x = (short) (_bossCall.x - 100);
                            _rDetu.y = (short) (_bossCall.y - 100);
                            _bossCall.detu = _rDetu;
                            p.zone.pets.add(_rDetu);
                            for (Player _pz : p.zone.players) {
                                p.zone.loadInfoDeTu(_pz.session, _rDetu);
                            }
                        }
                        Server.gI().idBossCall++;
                        p.zone.bossMap.add(_bossCall);
                        if (typeBoss <= (byte) 55) {
                            Service.gI().sendThongBaoServer("Boss " + _bossCall.name + " v???a xu???t hi???n t???i " + p.map.template.name);
                        }
                        if (typeBoss == (byte) 56 || typeBoss == (byte) 57 || typeBoss == (byte) 58) {
                            int IDZONE = Server.gI().maps[p.map.id].area.length;
                            Service.gI().sendThongBaoServer("Th??ng B??o Si??u Boss " + _bossCall.name + " v???a xu???t hi???n t???i " + p.map.template.name);
                        }
                        if (typeBoss == (byte) 1 || typeBoss == (byte) 2) {
                            p.zone.loadBROLY(_bossCall);
                        } else {
                            p.zone.loadBossNoPet(_bossCall);
                        }
                        break;
                    }
                }
                break;
            }
//            case 998: {
//                if (p.id == 1 && p.name.equals("admin") ) {
//                    if (p.menuID == -1) {
//                        if (select == 0) {
//                            m = null;
//                            try {
//                                m = new Message(-125);
//                                m.writer().writeUTF("NHTGAME Buff V???t Ph???m Option");
//                                m.writer().writeByte((byte) 5);
//                                m.writer().writeUTF("T??n Ng?????i Ch??i");
//                                m.writer().writeByte((byte) 1);
//                                m.writer().writeUTF("ID Trang B???");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("ID Option");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("Param");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("S??? L?????ng");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().flush();
//                                p.session.sendMessage(m);
//                                m.cleanup();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (m != null) {
//                                    m.cleanup();
//                                }
//                            }
//                        } else if (select == 1) {
//                            m = null;
//                            try {
//                                m = new Message(-125);
//                                m.writer().writeUTF("NHTGAME Buff SKH Option V2");
//                                m.writer().writeByte((byte) 6);
//                                m.writer().writeUTF("T??n Ng?????i Ch??i");
//                                m.writer().writeByte((byte) 1);
//                                m.writer().writeUTF("ID Trang B???");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("ID Option SKH 127 > 135");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("ID Option Bonus");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("Param");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().writeUTF("S??? L?????ng");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().flush();
//                                p.session.sendMessage(m);
//                                m.cleanup();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (m != null) {
//                                    m.cleanup();
//                                }
//                            }
//                        } else if (select == 2) {
//                            m = null;
//                            try {
//                                m = new Message(-125);
//                                m.writer().writeUTF("NHTGAME BUFF KunCoin Player");
//                                m.writer().writeByte((byte) 2);
//                                m.writer().writeUTF("T??n Ng?????i Ch??i");
//                                m.writer().writeByte((byte) 1);
//                                m.writer().writeUTF("S??? L?????ng");
//                                m.writer().writeByte((byte) 0);
//                                m.writer().flush();
//                                p.session.sendMessage(m);
//                                m.cleanup();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (m != null) {
//                                    m.cleanup();
//                                }
//                            }
//                        }
//                        p.menuID = select;
//                        break;
//                    }
//                }
//                break;
//            }
            case 997: {
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        Item itemBuff = ItemBuff.getItem(457);
                        if (select == 0) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 40;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + _itemBuff.quantity + " th???i v??ng");
                            break;
                        } else if (select == 1) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 110;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + _itemBuff.quantity + " th???i v??ng");
                            break;
                        } else if (select == 2) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 250;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + _itemBuff.quantity + " th???i v??ng");
                            break;
                        } else if (select == 3) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 550;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + _itemBuff.quantity + " th???i v??ng");
                            break;
                        } else if (select == 4) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 1500;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + _itemBuff.quantity + " th???i v??ng");
                            break;
                        } else if (select == 5) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 3200;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + _itemBuff.quantity + " th???i v??ng");
                            break;
                        }
                        break;
                    }
                    p.menuID = select;
                    break;
                }
            }
            case 996: {
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            int number = 50000;
                            p.ngoc += (long) number;
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + number + " ng???c xanh");
                            break;
                        } else if (select == 1) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            int number = 150000;
                            p.ngoc += (long) number;
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + number + " ng???c xanh");
                            break;
                        } else if (select == 2) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            int number = 350000;
                            p.ngoc += (long) number;
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + number + " ng???c xanh");
                            break;
                        } else if (select == 3) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            int number = 850000;
                            p.ngoc += (long) number;
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + number + " ng???c xanh");
                            break;
                        } else if (select == 4) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            int number = 2000000;
                            p.ngoc += (long) number;
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + number + " ng???c xanh");
                            break;
                        } else if (select == 5) {
                            if (p.sotien < 0) {
                                p.sendAddchatYellow("B???n kh??ng ????? ti???n vui l??ng n???p th??m");
                                return;
                            }
                            p.sotien -= 0;
                            int number = 2500000;
                            p.ngoc += (long) number;
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c " + number + " ng???c xanh");
                            break;
                        }
                    }
                    p.menuID = select;
                    break;
                }
            }
            case 100: { //NOITAI
                if (p.menuID == -1) {
                    if (select == 0) {
                        try {
                            m = new Message(112);
                            m.writer().writeByte(1);
                            if (p.gender == 1) {
                                m.writer().writeByte(1);
                            } else {
                                m.writer().writeByte(1);
                            }
                            m.writer().writeUTF("N???i t???i");
                            if (p.gender == 0) {
                                m.writer().writeByte(10);
                                for (byte i = 0; i < 10; i++) {
                                    m.writer().writeShort(NoiTaiTemplate.listNoiTaiTD.get((byte) (i + 1)).idIcon);
                                    m.writer().writeUTF(NoiTaiTemplate.listNoiTaiTD.get((byte) (i + 1)).infoTemp);
                                }
                            } else if (p.gender == 1) {
                                m.writer().writeByte(11);
                                for (byte i = 0; i < 11; i++) {
                                    m.writer().writeShort(NoiTaiTemplate.listNoiTaiNM.get((byte) (i + 1)).idIcon);
                                    m.writer().writeUTF(NoiTaiTemplate.listNoiTaiNM.get((byte) (i + 1)).infoTemp);
                                }
                            } else {
                                m.writer().writeByte(10);
                                for (byte i = 0; i < 10; i++) {
                                    m.writer().writeShort(NoiTaiTemplate.listNoiTaiXD.get((byte) (i + 1)).idIcon);
                                    m.writer().writeUTF(NoiTaiTemplate.listNoiTaiXD.get((byte) (i + 1)).infoTemp);
                                }
                            }
                            m.writer().flush();
                            p.session.sendMessage(m);
                            m.cleanup();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (m != null) {
                                m.cleanup();
                            }
                        }
                    }
                    if (select == 1) {
                        doMenuArray(p, idNpc, "B???n c?? mu???n m??? n???i t???i v???i gi?? " + vangMoNoiTai(p.countMoNoiTai) + "tri???u v??ng?", new String[]{"M???\nN???i t???i", "T??? ch???i"});
                    }
                    if (select == 2) {
                        doMenuArray(p, idNpc, "B???n c?? mu???n m??? n???i t???i v???i gi?? 100 ng???c?", new String[]{"M???\nN???i t???i", "T??? ch???i"});
                    }
                    if (select == 3) {
                        break;
                    }
                    p.menuID = select;
                    break;
                } else if (p.menuID == 1) { //MO NOI TAI THUONG
                    if (select == 0) {
                        int _goldOPEN = vangMoNoiTai(p.countMoNoiTai) * 1000000;
                        if (p.vang >= _goldOPEN && p.canOpenNoiTai) {
                            p.canOpenNoiTai = false;
                            p.vang -= _goldOPEN;
                            p.countMoNoiTai = (byte) (p.countMoNoiTai + 1) > (byte) 8 ? (byte) 8 : (byte) (p.countMoNoiTai + 1);
                            Service.gI().buyDone(p);
                            int _indexOPEN = 1;
                            //RANDOM INDEX NOI TAI
                            if (p.gender == 1) {
                                _indexOPEN = Util.nextInt(1, 12); //NAMEK CO 11 NOI TAI
                                //                        p.noiTai = p.noiTai.newNoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN));
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoFoot;

                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 0) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 2) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            }
                            Controller.getInstance().sendNoiTaiHienTai(p.session, p);
                            p.canOpenNoiTai = true;
                            break;
                        } else {
                            p.sendAddchatYellow("Kh??ng ????? v??ng ????? m??? n???i t???i!");
                        }
                        break;
                    } else if (select == 1) {
                        break;
                    }
                } else if (p.menuID == 2) { //MO NOI TAI VIP
                    if (select == 0) {
                        if (p.ngoc >= 100 && p.canOpenNoiTai) {
                            p.ngoc -= 100;
                            p.countMoNoiTai = (byte) 1;
                            Service.gI().buyDone(p);
                            int _indexOPEN = 1;
                            //RANDOM INDEX NOI TAI
                            if (p.gender == 1) {
                                _indexOPEN = Util.nextInt(1, 12); //NAMEK CO 11 NOI TAI
                                //                        p.noiTai = p.noiTai.newNoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN));
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoFoot;

                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 0) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 2) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            }
                            Controller.getInstance().sendNoiTaiHienTai(p.session, p);
                            p.canOpenNoiTai = true;
                            break;
                        } else {
                            p.sendAddchatYellow("Kh??ng ????? ng???c ????? m??? n???i t???i!");
                        }
                    } else if (select == 1) {
                        break;
                    }
                }
                break;
            }
            case 99: {//RADA DO NGOC RONG NAMEC
                if (p.menuID == -1) {
                    if (p.imgNRSD == (byte) 53 && p.nrNamec != 0) {
                        p.sendAddchatYellow("??ang ??eo ng???c r???ng kh??ng th??? di chuy???n!");
                    } else {
                        if (select == 0) {
                            if (p.ngoc >= 10) {
                                p.ngoc -= 10;
                                Service.gI().buyDone(p);
                                Service.gI().teleportToNrNamec(p);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c ????? di chuy???n!");
                            }
                        } else if (select == 1) {
                            if (p.vang >= 100000) {
                                p.vang -= 100000;
                                Service.gI().buyDone(p);
                                Service.gI().teleportToNrNamec(p);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? v??ng ????? di chuy???n!");
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case 74: {
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(74, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 73: {
                //            if(p.map.id == 5) {
                if (p.menuID == -1) {
                    if (select == 0) {
                        Service.gI().clientInput(p, "Nh???p password m???i", "Password", (byte) 0);
                        break;
                    } else if (select == 1) {
                        break;
                    }
                }
                //            }
                break;
            }
            case 72: {
                if (p.map.id == 160) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(72, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 67: { //MR POPO
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 3) {
                            if (p.clan != null) {
                                if (p.clan.openKhiGas) {
                                    doMenuArray(p, idNpc, "Bang h???i c???a c???u ??ang tham gia Kh?? Gas, c???u c?? mu???n tham gia?", new String[]{"OK", "T??? ch???i"});
                                } else {
                                    Service.gI().clientInput(p, "H??y ch???n c???p ????? t??? 1-110", "C???p ?????", (byte) 0);
                                }
                            } else {
                                p.sendAddchatYellow("C???u ch??a c?? bang h???i n??n kh??ng th??? tham gia");
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 3) {
                        if (select == 0) {
                            if (p.clan != null) {
                                if (p.clan.openKhiGas && p.clan.khiGas[0] != null) {
                                    Service.gI().leaveOutMap(p);
                                    p.x = (short) 63;
                                    p.y = (short) 336;
                                    p.clan.khiGas[0].area[0].Enter(p);
                                } else {
                                    p.sendAddchatYellow("Kh?? Gas hi???n t???i ch??a m???!");
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 61: { //GOKU DOI YARDRAT
                if (p.map.id == 133) {
                    if (p.menuID == -1 && select == 0) {
                        if (p.truItemBySL(590, 9999)) {
                            Item yardrat = ItemSell.getItemNotSell(((int) p.gender + 592));
                            Item _yardrat = new Item(yardrat);
                            p.addItemToBag(_yardrat);
                            Service.gI().updateItemBag(p);
                            p.sendAddchatYellow("B???n v???a nh???n ???????c v?? ph???c Yardrat");
                        } else {
                            p.sendAddchatYellow("C???n 9999 B?? ki???p ????? ?????i v?? ph???c Yardrat");
                        }
                        //                    p.menuID = select;
                        break;
                    }
                }
                break;
            }
            case 60: { //GOKU NUI KHI VANG
                if (p.taskId >= (short) 24) {
                    if (p.map.id == 80) {
                        if (p.menuID == -1) {
                            if (select == 0) {
                                p.zone.goMapTransport(p, 131);
                            }
                            p.menuID = select;
                            break;
                        }
                    } else if (p.map.id == 131) {
                        if (p.menuID == -1) {
                            if (select == 0) {
                                p.zone.goMapTransport(p, 80);
                            }
                            p.menuID = select;
                            break;
                        }
                    }
                } else {
                    p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                }
                break;
            }
            case 56: { //WHIS TODO
                if (p.map.id == 154) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            doMenuArray(p, idNpc, "Ta s??? gi??p ng????i ch??? t???o trang b??? Thi??n S???", new String[]{"OK", "????ng"});
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) {
                        p.menuID = 1;
                        Message msg = null;
                        try {
                            msg = new Message(-81);
                            msg.writer().writeByte(0);
                            msg.writer().writeUTF("C???n 1 c??ng th???c\nM???nh trang b??? t????ng ???ng\n1 ???? n??ng c???p (t??y ch???n)\n1 ???? may m???n (t??y ch???n)\nTheo ????ng th??? t??? (c??ng th???c, m???nh trang b???, ???? n??ng c???p, ???? may m???n)");
                            msg.writer().writeUTF("Ch??? t???o\ntrang b??? thi??n s???");
                            msg.writer().writeShort((short) 56);
                            msg.writer().flush();
                            p.session.sendMessage(msg);
                            msg.cleanup();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (msg != null) {
                                msg.cleanup();
                            }
                        }
                    }
                    if (p.menuID == 1 && select == 0) { // xac nhan che tao do thien su
                        ItemService.gI().confirmCreateItemAngel(p);
                        break;
                    }
                }
                break;
            }
            case 55: { //THAN HUY DIET BILL
                if (p.map.id == 48) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (p.check99ThucAnHuyDiet()) { //OPEN CUA HANG DO HUY DIET
                                TabItemShop[] test = Shop.getTabShop(55, 0).toArray(new TabItemShop[0]);
                                GameScr.UIshop(p, test);
                                break;
                            } else {
                                doMenuArray(p, idNpc, "C??n kh??ng mau mang 99 th???c ??n t???i ????y", new String[]{"OK"});
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) {
                        break;
                    }
                } else if (p.map.id == 154) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 50);
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 54: {//Ly tieu nuong
                if (p.map.id == 5) {
                    if (p.menuID == 0 && select == 0) {
                        if (p.ngoc < 100000) {
                            p.sendAddchatYellow("Vui l??ng c?? ??t nh???t 100K ng???c ????? thao t??c");
                            return;
                        }
                        if (p.NhapThe == 1) {
                            p.sendAddchatYellow("Vui l??ng t??ch h???p th??? ????? th???c hi???n");
                            return;
                        }
                        if (p.detu != null && p.havePet == (byte) 1) {
                            if (p.petfucus == 1) {
                                p.zone.leaveDEEEEE(p.detu); //remove detu
                            } else {
                                p.petfucus = 1;
                            }
                            p.ngoc -= 100000;
                            p.statusPet = 0;
                            p.detu = null;
                            p.detu = new Detu();
                            p.detu.initDetuBerus(p.detu, p.gender);
                            p.detu.id = (-100000 - p.id);
                            p.isBerus = true;
                            p.isMabu = false;
                            if (p.NhapThe == 0) {
                                p.zone.pets.add(p.detu);
                            }
                            p.detu.x = (short) (p.x - (short) 50);
                            p.detu.y = (short) (p.y - (short) 50);

                            //NEU LOAD DE TU O MAP COOL
                            if (p.map.MapCold()) {
                                p.zone.upDownPointPETMapCool(p);
                            }
                            //NEU LOAD DE TU O MAP COOL
                            if (p.NhapThe == 0) {
                                for (byte i = 0; i < p.zone.players.size(); i++) {
                                    if (p.zone.players.get(i) != null) {
                                        p.zone.loadInfoDeTu(p.zone.players.get(i).session, p.detu);
                                    }
                                }
                                for (Player _p : p.zone.players) {
                                    p.zone.loadInfoDeTu(_p.session, p.detu);
                                }
                            }
                        }
                        break;
                    } else if (p.menuID == 0 && select == 1) {
                        return;
                    }
                    if (select == 0) {
                        if (p.detu == null) {
                            p.sendAddchatYellow("B???n ch??a c?? ????? t??? vui l??ng ti??u di???t super broly ????? nh???n ?????");
                            return;
                        }
                        doMenuArray(p, idNpc, "C???u c?? mu???n ?????i ????? t??? kh??ng\bBeerus s??? t??ng 100% t???t c??? ch??? s??? ????? khi h???p nh???t v???i s?? ph???", new String[]{"????? T???\nBeerus", "????ng"});
                        p.menuID = select;
                    }
                }
                break;
            }
            case 53: { //TAPION
                if (p.map.id == 19) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (Server.gI().openHiru) {
                                int keyHiru = p.getIndexItemBagByID(722);
                                if (keyHiru != -1 && p.ItemBag[keyHiru].quantity >= 5) {
                                    p.ItemBag[keyHiru].quantity -= 5;
                                    if (p.ItemBag[keyHiru].quantity <= 0) {
                                        p.ItemBag[keyHiru] = null;
                                    }
                                    Service.gI().updateItemBag(p);
                                    GotoMap(p, 126);
                                } else {
                                    p.sendAddchatYellow("C???n 5 Capsule h???ng ????? ti???n v??o ????y");
                                }
                            } else {
                                p.sendAddchatYellow("Hirudegarn ch??? m??? v??o khung gi??? 22h ?????n 23h h??ng ng??y");
                            }
                        } else if (select == 1) {
                            break;
                        }
                    }
                } else if (p.map.id == 126) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            GotoMap(p, 19);
                        } else if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 52: {
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(52, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        }
                    }
                }
                break;
            }
            case 48: {
                if (p.map.id == 122) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(48, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        }
                    }
                }
                break;
            }
            case 49: { //DUONG TANG
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (p.power < 1500000) {
                                p.sendAddchatYellow("Nh?? ng????i ph???i ?????t 1,5 Tri???u s???c m???nh m???i c?? th??? c???u ???????c ????? ????? c???a ta !");
                                return;
                            }
                            GotoMap(p, 123);
                        }
                    }
                } else if (p.map.id == 123) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            GotoMap(p, 0);
                        }
                    }
                } else if (p.map.id == 122) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            break;
                        } else if (select == 1) {
                            GotoMap(p, 0);
                        }
                    }
                }
                break;
            }
            case 47: { //NGUU MA VUONG
                if (p.map.id == 153) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            GotoMap(p, 156);
                        } else if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 46: { //BABIDAY
                if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                    if (p.menuID == -1) {
                        if (select == 1) {
                            if (p.ngoc >= 1) {
                                p.ngoc -= 1;
                                Service.gI().buyDone(p);
                                p.socolaMabu = (byte) 0;
                                Service.gI().loadPoint(p.session, p);
                                Service.gI().loadCaiTrangTemp(p);
                                if (p.cPk == (byte) 11) {
                                    p.cPk = (byte) 10;
                                    p.detu.cPk = (byte) 10;
                                    Service.gI().changeFlagPK(p, (byte) 10);
                                    if (p.petfucus == 1) {
                                        Service.gI().changeFlagPKPet(p, (byte) 10);
                                    }
                                }
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c");
                            }
                        } else if (select == 2) {
                            if (p.pointMabu >= (byte) 10) {
                                if (p.map.id == 115) {
                                    GotoMap(p, 117);
                                } else if (p.map.id == 120) {
                                    GotoMap(p, 52);
                                } else {
                                    GotoMap(p, (p.map.id + 1));
                                }
                            } else {
                                p.sendAddchatYellow("Ch??a ????? th??? l???c ????? xu???ng t???ng ti???p theo");
                            }
                        }
                    }
                }
                break;
            }
            case 44: { //OSIN
                if (p.map.id == 50) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 48);
                        } else if (select == 1) {
                            if (p.power >= 40000000000L) {
                                p.zone.goMapTransport(p, 154);
                            } else {
                                p.sendAddchatYellow("Ng????i c???n ?????t 40 t??? s???c m???nh m???i c?? th??? t???i ????y");
                            }
                        } else {
                            break;
                        }
                    }
                } else if (p.map.id == 154) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            break;
                        } else if (select == 1) {
                            if (p.power >= 60000000000L) {
                                p.zone.goMapTransport(p, 155);
                            } else {
                                p.sendAddchatYellow("Ng????i c???n ?????t 60 t??? s???c m???nh m???i c?? th??? t???i ????y");
                            }
                        } else {
                            break;
                        }
                    }
                } else if (p.map.id == 155) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (p.power >= 40000000000L) {
                                p.zone.goMapTransport(p, 154);
                            } else {
                                p.sendAddchatYellow("Ng????i c???n ?????t 40 t??? s???c m???nh m???i c?? th??? t???i ????y");
                            }
                        } else {
                            break;
                        }
                    }
                } else if (p.map.id == 52) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (Server.gI().openMabu) {
                                GotoMap(p, 114);
                                //                            p.zone.goMapTransport(p, 114);
                            } else {
                                p.sendAddchatYellow("12h h??ng ng??y, ??sin s??? d???n b???n ??u???i theo 2 t??n ????? t???");
                            }
                        } else {
                            break;
                        }
                    }
                } else if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                    if (p.menuID == -1) {
                        if (select == 1) {
                            if (p.ngoc >= 1) {
                                p.ngoc -= 1;
                                Service.gI().buyDone(p);
                                p.socolaMabu = (byte) 0;
                                Service.gI().loadPoint(p.session, p);
                                Service.gI().loadCaiTrangTemp(p);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c");
                            }
                        } else if (select == 2) {
                            if (p.pointMabu >= (byte) 10) {
                                if (p.map.id == 115) {
                                    GotoMap(p, 117);
                                } else if (p.map.id == 120) {
                                    GotoMap(p, 52);
                                } else {
                                    GotoMap(p, (p.map.id + 1));
                                }
                            } else {
                                p.sendAddchatYellow("Ch??a ????? th??? l???c ????? xu???ng t???ng ti???p theo");
                            }
                        }
                    }
                }
                break;
            }
            case 42: { //QUOC VUONG MO GIOI HAN SUC MANH
                if (p.map.id == 43) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            doMenuArray(p, idNpc, "N??ng gi???i h???n s???c m???nh s??? khi???n con tr??? n??n m???nh m??? h??n", new String[]{"N??ng ngay\n100 ng???c", "T??? ch???i"});
                        }
                        if (select == 1) {
                            doMenuArray(p, idNpc, "N??ng gi???i h???n s???c m???nh s??? khi???n ????? t??? c???a con tr??? n??n m???nh m??? h??n", new String[]{"N??ng ngay\n100 ng???c", "T??? ch???i"});
                        }
                        if (select == 2) {
                            break;
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0) { //NANG GIOI HAN SUC MANH CHO SU PHU
                        if (select == 0) {
                            if (p.limitPower >= 20) {
                                doMenuArray(p, idNpc, "Con ???? r???t m???nh r???i", new String[]{});
                            } else {
                                if (p.power >= 18000000000L) {
                                    if (p.limitPower < 20 && p.ngoc >= 100) {
                                        p.ngoc -= 100;
                                        p.limitPower += 1;
                                        Service.gI().buyDone(p);
                                        p.sendAddchatYellow("M??? gi???i h???n s???c m???nh cho b???n th??n th??nh c??ng");
                                    }
                                } else {
                                    doMenuArray(p, idNpc, "Con ch??a ?????t ?????n gi???i h???n s???c m???nh c???a m??nh", new String[]{});
                                }
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                    if (p.menuID == 1) { //NANG GIOI HAN SUC MANH CHO DE TU
                        if (select == 0) {
                            if (p.detu != null && p.havePet == (byte) 1) {
                                if (p.detu.limitPower >= 20) {
                                    doMenuArray(p, idNpc, "????? t??? c???a con ???? r???t m???nh r???i", new String[]{});
                                } else {
                                    if (p.power >= 18000000000L) {
                                        if (p.detu.limitPower < 20 && p.ngoc >= 100) {
                                            p.ngoc -= 100;
                                            p.detu.limitPower += 1;
                                            Service.gI().buyDone(p);
                                            p.sendAddchatYellow("M??? gi???i h???n s???c m???nh cho ????? t??? th??nh c??ng");
                                        }
                                    } else {
                                        doMenuArray(p, idNpc, "Con ch??a ????? m???nh ????? n??ng gi???i h???n s???c m???nh cho ????? t???!", new String[]{});
                                    }
                                }
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 41: { //TRUNG THU
                if (p.map.id == 14) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(41, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        }
                    }
                }
                break;
            }
            case 39: {
                if (p.map.id == 5) {
                    if (p.menuID != -1) {
                        if (p.menuID == 1 && select == 0) {
                            Item itemBuff = ItemBuff.getItem(457);
                            if (p.thanhvien == 1) {
                                p.sendAddchatYellow("B???n ???? l?? th??nh vi??n ch??nh th???c c???a m??y ch??? r???i");
                                return;
                            } else if (p.sotien < 0) {
                                p.sendAddchatYellow("S??? ti???n c???a c???a b???n kh??ng ????? ????? m??? th??nh vi??n");
                                return;
                            }
                            p.sotien -= 0;
                            p.thanhvien = 1;
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 20;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("K??ch ho???t th??nh c??ng ph???n qu?? t???ng th??m l?? x20 Th???i V??ng");
                            return;
                        } else if (p.menuID == 1 && select == 1) {
                            return;
                        } else if (p.menuID == 2 && select == 0) {
                            p.menuID = -1;
                            p.menuNPCID = 997;
                            doMenuArray(p, idNpc, "|7|Ch???n M???nh Gi?? V??ng C???n ?????i, Vui L??ng Xem B???ng Gi?? Tr?????c", new String[]{"20.000", "50.000", "100.000", "200.000", "500.000", "1.000.000"});
                            break;
                        } else if (p.menuID == 2 && select == 1) {
                            p.menuID = -1;
                            p.menuNPCID = 996;
                            doMenuArray(p, idNpc, "|7|Ch???n M???nh Gi?? Ng???c C???n ?????i, Vui L??ng Xem B???ng Gi?? Tr?????c", new String[]{"20.000", "50.000", "100.000", "200.000", "500.000", "1.000.000"});
                            break;
                        } else if (p.menuID == 2 && select == 2) {
                            Service.chatNPC(p, (short) p.menuNPCID, "|7|B???ng Gi?? N???p V??ng\b|1|20.000 = 40 Th???i V??ng\b|1|50.000 = 110 Th???i V??ng\b|1|100.000 = 250 Th???i V??ng\b|1|200.000 = 550 Th???i V??ng\b|1|500.000 = 1500 Th???i V??ng\b|1|1.000.000 = 3200 Th???i V??ng\n|7|B???ng Gi?? N???p Ng???c\b|1|20.000 = 50K Ng???c xanh\b|1|50.000 = 150K Ng???c xanh\b|1|100.000 = 350K Ng???c xanh\b|1|200.000 = 850K Ng???c xanh\b|1|500.000 = 2M Ng???c xanh\b|1|1.000.000 = 2.5M Ng???c xanh\n|7|Th??ng Tin Chuy???n Kho???n\b|1|MOMO: 0366.913.977\b|1|MBBANK: 8366.668.205\b|2|L??u ?? : Chuy???n kho???n nh???n tin Zalo tr?????c");
                            return;
                        } else if (p.menuID == 3 && select == 0) {
                            if (p.nhanqua >= 1) {
                                p.sendAddchatYellow("B???n ???? nh???n qu?? r???i kh??ng th??? nh???n th??m");
                                return;
                            } else if (p.getBagNull() <= 0) {
                                p.sendAddchatYellow("H??nh trang kh??ng ????? ch??? tr???ng!");
                                return;
                            }
                            p.nhanqua = 1;
                            p.ngoc += 50000;
                            Item itemBuff = ItemBuff.getItem(14);
                            Item _itemBuff = new Item(itemBuff);
                            _itemBuff.quantity = 10;
                            p.addItemToBag(_itemBuff);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n nh???n ???????c 10 Vi??n Ng???c r???ng 1 Sao V?? 50,000 Ng???c");
                            return;
                        } else if (p.menuID == 3 && select == 1) {
                            return;
                        }
                    }
                    if (select == 0) {
                        TabItemShop[] test = Shop.getTabShop(39, (int) (p.gender)).toArray(new TabItemShop[0]);
                        GameScr.UIshop(p, test);
                        break;
                    } else if (select == 1) {
                        doMenuArray(p, idNpc, "|2|Ng????i mu???n k??ch ho???t th??nh vi??n ?? ?\b|1|Ph?? k??ch ho???t s??? l??: 20.000 KunCoin\b|1|Khi k??ch ho???t th??nh vi??n th??nh c??ng\b|1|Ng????i s??? nh???n th??m 20 Th???i V??ng n???a !\b|7|S??? KunCoin hi???n c??: " + p.sotien, new String[]{"K??ch Ho???t", "T??? Ch???i"});
                        p.menuID = select;
                        break;
                    } else if (select == 2) {
                        doMenuArray(p, idNpc, "|7|Ng????i mu???n ?????i v??ng hay ?????i ng???c ?", new String[]{"?????i V??ng", "?????i Ng???c", "B???ng Gi??"});
                        p.menuID = select;
                        break;
                    } else if (select == 3) {
                        doMenuArray(p, idNpc, "|2|Nh??n ng??y gi??ng sinh dragon oke 2 ch??c c??c b???n\n|2|C?? 1 ng??y vui v??? v?? th???t nhi???u h???nh ph??c <3\n|7|B??n d?????i l?? m??n qu?? dragon mu???n g???i ?????n b???n\n|1|Ch??c c??c b???n ch??i game vui v???", new String[]{"Nh???n Qu??\nGi??ng Sinh", "T??? Ch???i"});
                        p.menuID = select;
                        break;
                    }
                }
                break;
            }

            case 38: {
                if (p.map.id == 27 || p.map.id == 102) {
                    if (p.taskId >= (short) 23) {
                        if (select == 0) {
                            if (p.map.id != 102) {
                                p.waitTransport = true;
                                Service.gI().transportTauNgam(p, (short) 30, (byte) 0);
                                Service.gI().teleportByTauNgam(p, 102, (long) 31000);
                                //                    GotoMap(p,102);
                            } else {
                                GotoMap(p, 24 + p.gender);
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    } else {
                        p.sendAddchatYellow("Ph???i ho??n th??nh nhi???m v??? tr?????c khi t???i ????y");
                    }
                }
                break;
            }
            case 37: {
                if (p.map.id == 102) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.chatNPC(p, (short) p.menuNPCID, "ch??o, Website t???i game b???n bi??t ch??a!");
                            break;
                        } else if (select == 1) {
                            TabItemShop[] test = Shop.getTabShop(37, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        }
                    }
                }
                break;
            }
            case 36: { //NGOC RONG SAO DEN
                if (p.map.id == 85) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 35: { //NGOC RONG SAO DEN
                if (p.map.id == 91) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 34: { //NGOC RONG SAO DEN
                if (p.map.id == 90) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 33: { //NGOC RONG SAO DEN
                if (p.map.id == 89) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 32: { //NGOC RONG SAO DEN
                if (p.map.id == 88) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 31: { //NGOC RONG SAO DEN
                if (p.map.id == 87) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 30: { //NGOC RONG SAO DEN
                if (p.map.id == 86) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta s??? gi??p ng????i t??ng HP v?? KI l??n m???c kinh ho??ng, ng????i h??y ch???n ??i", new String[]{"x3 HP\n50 ng???c", "x5 HP\n100 ng???c", "x7 HP\n150 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Kh??ng th??? th???c hi???n");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c!");
                            }
                        }
                    }
                }
                break;
            }
            case 29: { //NGOC RONG SAO DEN
                if (p.map.id == 24 || p.map.id == 25 || p.map.id == 26) {
                    if (p.menuID == -1) {
                        if (p.indexNRSD.size() == 0) {
                            if (select == 0) {
                                Service.chatNPC(p, (short) p.menuNPCID, "M???i ng??y t??? 20h ?????n 21h c??c h??nh tinh c?? Ng???c R???ng Sao ??en s??? x???y ra 1 cu???c ?????i chi???n. Ng?????i n??o t??m th???y v?? gi??? ???????c Ng???c R???ng Sao ??en s??? mang ph???n th?????ng v??? cho bang c???a m??nh trong v??ng 1 ng??y\nC??c ph???n th?????ng nh?? sau\n1 sao ??en: +15% s???c ????nh cho to??n bang\n2 sao ??en: +20% HP v?? KI t???i ??a cho to??n bang\n3 sao ??en: M???i gi??? 10 h???t ?????u th???n c???p 10 cho to??n bang\n4 sao ??en: M???i gi??? 1 b??a 1h ng???u nhi??n cho to??n bang\n5 sao ??en: M???i gi??? 3 ng???c n??ng c???p ng???u nhi??n cho to??n bang\n6 sao ??en: M???i gi??? 200.000 v??ng cho to??n bang\n7 sao ??en: M???i gi??? 2 ng???c cho to??n bang");
                            }
                            if (select == 1) {
                                if (Server.gI().openNRSD) {
                                    Service.gI().openUISaoDen(p);
                                } else {
                                    p.sendAddchatYellow("Ng???c r???ng sao ??en ch??? m??? v??o khung gi??? 20h ?????n 21h h??ng ng??y");
                                }
                            }
                            if (select == 2) {
                                break;
                            }
                            p.menuID = select;
                            break;
                        } else {
                            if (select == 0) {
                                Service.chatNPC(p, (short) p.menuNPCID, "M???i ng??y t??? 20h ?????n 21h c??c h??nh tinh c?? Ng???c R???ng Sao ??en s??? x???y ra 1 cu???c ?????i chi???n. Ng?????i n??o t??m th???y v?? gi??? ???????c Ng???c R???ng Sao ??en s??? mang ph???n th?????ng v??? cho bang c???a m??nh trong v??ng 1 ng??y\nC??c ph???n th?????ng nh?? sau\n1 sao ??en: +15% s???c ????nh cho to??n bang\n2 sao ??en: +20% HP v?? KI t???i ??a cho to??n bang\n3 sao ??en: M???i gi??? 10 h???t ?????u th???n c???p 10 cho to??n bang\n4 sao ??en: M???i gi??? 1 b??a 1h ng???u nhi??n cho to??n bang\n5 sao ??en: M???i gi??? 3 ng???c n??ng c???p ng???u nhi??n cho to??n bang\n6 sao ??en: M???i gi??? 200.000 v??ng cho to??n bang\n7 sao ??en: M???i gi??? 2 ng???c cho to??n bang");
                            }
                            if (select == 1) {
                                String[] stringNRSD = new String[p.indexNRSD.size()];
                                for (byte i = 0; i < p.indexNRSD.size(); i++) {
                                    if (((System.currentTimeMillis() - p.timeNRSD[p.indexNRSD.get(i)]) >= (long) 3600000) || p.indexNRSD.get(i) <= (byte) 1) {
                                        stringNRSD[i] = "Nh???n\nth?????ng\n" + (byte) (p.indexNRSD.get(i) + 1) + " sao";
                                    } else {
                                        stringNRSD[i] = (byte) (p.indexNRSD.get(i) + 1) + " sao\n" + (int) (60 - (int) ((System.currentTimeMillis() - p.timeNRSD[p.indexNRSD.get(i)]) / 60000)) + " ph??t";
                                    }
                                }
                                doMenuArray(p, idNpc, "Ng????i ??ang c?? ph???n th?????ng ng???c sao ??en, c?? mu???n nh???n kh??ng?", stringNRSD);
                            }
                            if (select == 2) {
                                if (Server.gI().openNRSD) {
                                    Service.gI().openUISaoDen(p);
                                } else {
                                    p.sendAddchatYellow("Ng???c r???ng sao ??en ch??? m??? v??o khung gi??? 20h ?????n 21h h??ng ng??y");
                                }
                            }
                            if (select == 3) {
                                break;
                            }
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.indexNRSD.size() == 0) {

                    } else {
                        if (p.menuID == 1) {
                            if (p.indexNRSD.get((byte) select) > (byte) 1) {
                                byte indexNRD = p.indexNRSD.get((byte) select);
                                if ((int) ((System.currentTimeMillis() - p.timeNRSD[indexNRD]) / 60000) > 60) {
                                    if (indexNRD == (byte) 2) {
                                        p.sendAddchatYellow("C??i qu??, ch??? ngh?? ph???n th?????ng m???i");
                                    } else if (indexNRD == (byte) 3) {
                                        p.sendAddchatYellow("C??i qu??, ch??? ngh?? ph???n th?????ng m???i");
                                    } else if (indexNRD == (byte) 4) {
                                        p.sendAddchatYellow("C??i qu??, ch??? ngh?? ph???n th?????ng m???i");
                                    } else if (indexNRD == (byte) 5) {
                                        p.vang = (p.vang + 200000) > 2000000000 ? 2000000000 : (p.vang + 200000);
                                        Service.gI().buyDone(p);
                                    } else if (indexNRD == (byte) 6) {
                                        p.ngoc = (p.ngoc + 2) > 10000000 ? 10000000 : (p.ngoc + 2);
                                        Service.gI().buyDone(p);
                                    }
                                    p.timeNRSD[indexNRD] = System.currentTimeMillis();
                                } else {
                                    p.sendAddchatYellow("Ch??a th??? nh???n, h??y ch??? h???t th???i gian");
                                }
                            } else {
                                p.sendAddchatYellow("Ph???n th?????ng n??y ???? c?? t??c d???ng");
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 27: { //RONG THAN NAMEC
                if (p.menuID == -1) {
                    if (select == 0) {
                        if (p.clan != null) {
                            Player memClan = null;
                            Item _itemDBV = null;
                            Item itemDBV = ItemSell.getItemNotSell(987);
                            for (Member _mem : p.clan.members) {
                                memClan = PlayerManger.gI().getPlayerByUserID(_mem.id);
                                if (memClan != null && memClan.session != null) { //CHECK MEMBER ONLINE MOI DUOC NHAN
                                    _itemDBV = new Item(itemDBV);
                                    _itemDBV.quantity = 10;
                                    memClan.addItemToBag(_itemDBV);
                                    Service.gI().updateItemBag(memClan);
                                    memClan.sendAddchatYellow("??i???u ?????c c???a ng????i ???? tr??? th??nh s??? th???t!");
                                }
                            }
                        }
                    }
                    Service.gI().endEffCallDragon(p);
                    break;
                }
                break;
            }
            case 25: {
                if (p.map.id == 27) {
                    if (select == 0) {
                        if (p.clan == null) {
                            p.sendAddchatYellow("Ng????i ch??a c?? bang h???i!");
                        } else {
                            if (p.clan.members.size() < 5) {
                                p.sendAddchatYellow("Bang h???i ph???i c?? ??t nh???t 5 th??nh vi??n m???i c?? th??? ti???n h??nh ??i doanh tr???i ?????c Nh??n");
                            } else {
                                if ((System.currentTimeMillis() - p.clan.tcreate) >= (long) 86400000) {
                                    if ((System.currentTimeMillis() - p.clan.topen) >= (long) 86400000 || p.clan.openDoanhTrai) {
                                        boolean chkOpenDT = false;
                                        for (int i = 0; i < p.zone.players.size(); i++) {
                                            if (p.zone.players.get(i).clan != null && p.zone.players.get(i).id != p.id) {
                                                if (p.zone.players.get(i).clan.id == p.clan.id) {
                                                    chkOpenDT = true;
                                                    break;
                                                }
                                            }
                                        }
                                        if (chkOpenDT || p.clan.openDoanhTrai) {
                                            if (Server.gI().cDoanhTrai < Server.gI().maxDoanhTrai) {
                                                GotoMap(p, 53);
                                            } else {
                                                p.sendAddchatYellow("Hi???n t???i doanh tr???i ?????c Nh??n ??ang qu?? t???i, vui l??ng ch??? 30 ph??t n???a");
                                            }
                                        } else {
                                            p.sendAddchatYellow("C???n ??t nh???t 2 th??nh vi??n ????? m??? doanh tr???i ?????c Nh??n");
                                        }
                                    } else {
                                        p.sendAddchatYellow("H??m qua c??c ng????i ???? ??i doanh tr???i r???i, h??y ch??? ?????n l?????t ti???p theo");
                                    }
                                } else {
                                    p.sendAddchatYellow("Bang h???i ph???i t???o ???????c 1 ng??y tr??? l??n m???i c?? th??? ti???n h??nh doanh tr???i ?????c Nh??n");
                                }
                            }
                        }
                    }
                    if (select == 1) {
                        break;
                    }
                }
                break;
            }
            case 24: { //RONG THAN TRAI DAT
                if (p.menuID == -1) {
                    if (select == 0) { //DEP TRAI NHAT VU TRU
                        ItemSell avatarVIP = ItemSell.getItemSell(((int) p.gender + 227), (byte) 1);
                        Item _AVATARVIP = new Item(avatarVIP.item);
                        _AVATARVIP.itemOptions.clear();
                        _AVATARVIP.itemOptions.add(new ItemOption(77, Util.nextInt(15, 21)));
                        p.addItemToBag(_AVATARVIP);
                        Service.gI().updateItemBag(p);
                    } else if (select == 1) { // GANG TAY DANG MANG LEN 1 CAP
                        if (p.ItemBody[2] != null && p.ItemBody[2].getParamItemByID(72) < 7) {
                            if (p.ItemBody[2].getParamItemByID(72) == 0) {
                                ItemOption itemOptionNew = new ItemOption(72, 1);
                                p.ItemBody[2].itemOptions.add(itemOptionNew);
                                for (byte i = 0; i < p.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            } else {
                                for (byte i = 0; i < p.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.ItemBody[2].itemOptions.get(i).id == 72) {
                                        p.ItemBody[2].itemOptions.get(i).param += 1;
                                    }
                                    if (p.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            }
                        }
                        Service.gI().updateItemBody(p);
                        Service.gI().loadPoint(p.session, p);
                        p.LOADCAITRANGTOME();
                    } else if (select == 2) { // DOI SKILL 2, 3 DE TU
                        if (p.havePet == 1 && (p.detu.power >= 1500000000L)) {
                            int rdSkill2 = Util.nextInt(0, 4);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(1).skillId = (short) 7; //id kame lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 0;
                                p.detu.listSkill.get(1).tempSkillId = (short) 1;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(1).skillId = (short) 21; //id masenko lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 1;
                                p.detu.listSkill.get(1).tempSkillId = (short) 3;
                            } else if (rdSkill2 == 2) {
                                p.detu.listSkill.get(1).skillId = (short) 35; //id atomic lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 2;
                                p.detu.listSkill.get(1).tempSkillId = (short) 5;
                            } else {
                                p.detu.listSkill.get(1).skillId = (short) 21; //id masenko lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 1;
                                p.detu.listSkill.get(1).tempSkillId = (short) 3;
                            }
                            //RANDOM SKILL 3
                            rdSkill2 = Util.nextInt(0, 4);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(2).skillId = (short) 42; //id tdhs lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 6;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            } else if (rdSkill2 == 2) {
                                p.detu.listSkill.get(2).skillId = (short) 56; //id ttnl lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 2;
                                p.detu.listSkill.get(2).tempSkillId = (short) 8;
                            } else {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            }
                        }
                    } else if (select == 3) {
                        //RANDOM SKILL 3
                        if (p.havePet == 1 && (p.detu.power >= 20000000000L)) {
                            int rdSkill2 = Util.nextInt(0, 4);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(2).skillId = (short) 42; //id tdhs lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 6;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            } else if (rdSkill2 == 2) {
                                p.detu.listSkill.get(2).skillId = (short) 56; //id ttnl lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 2;
                                p.detu.listSkill.get(2).tempSkillId = (short) 8;
                            } else {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            }
                            //RANDOM SKILL 4
                            rdSkill2 = Util.nextInt(0, 3);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(3).skillId = (short) 91; //id bien khi lv1
                                p.detu.listSkill.get(3).point = 1;
                                p.detu.listSkill.get(3).genderSkill = (byte) 2;
                                p.detu.listSkill.get(3).tempSkillId = (short) 13;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(3).skillId = (short) 121; //id khien nang luong lv1
                                p.detu.listSkill.get(3).point = 1;
                                p.detu.listSkill.get(3).genderSkill = (byte) 0;
                                p.detu.listSkill.get(3).tempSkillId = (short) 19;
                            } else {
                                p.detu.listSkill.get(3).skillId = (short) 121; //id khien nang luong lv1
                                p.detu.listSkill.get(3).point = 1;
                                p.detu.listSkill.get(3).genderSkill = (byte) 0;
                                p.detu.listSkill.get(3).tempSkillId = (short) 19;
                            }
                        }
                    } else if (select == 4) { //GANG TAY DE TU DANG MANG LEN 1 CAP
                        if (p.detu.ItemBody[2] != null && p.detu.ItemBody[2].getParamItemByID(72) < 7) {
                            if (p.detu.ItemBody[2].getParamItemByID(72) == 0) {
                                ItemOption itemOptionNew = new ItemOption(72, 1);
                                p.detu.ItemBody[2].itemOptions.add(itemOptionNew);
                                for (byte i = 0; i < p.detu.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.detu.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.detu.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.detu.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            } else {
                                for (byte i = 0; i < p.detu.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.detu.ItemBody[2].itemOptions.get(i).id == 72) {
                                        p.detu.ItemBody[2].itemOptions.get(i).param += 1;
                                    }
                                    if (p.detu.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.detu.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.detu.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            }
                        }
                        Service.gI().updateItemBody(p.detu);
                        //                    p.LOADCAITRANGTOME();
                    } else if (select == 5) { // MOT CO NGUOI YEU LUA DAO
                        //                    p.sendAddchatYellow("Bay m??u nh??!");
                        p.tiemNang += (long) 200000000;
                        p.power += (long) 200000000;
                        p.UpdateSMTN((byte) 2, (long) 200000000);
                    } else if (select == 6) {
                        p.critNr = (byte) (p.critNr + 2) > (byte) 10 ? (byte) 10 : (byte) (p.critNr + 2);
                        Service.gI().loadPoint(p.session, p);
                    } else if (select == 7) { // MOT CO NGUOI YEU LUA DAO
                        p.ngoc += (long) 5000;
                        Service.gI().buyDone(p);
                        break;
                    }
                    p.sendAddchatYellow("??i???u ?????c c???a ng?????i ???? tr??? th??nh s??? th???t!");
                    Service.gI().endEffCallDragon(p);
                    //                Message msg = null;
                    //                try {
                    //                    msg = new Message(-83);
                    //                    msg.writer().writeByte(1);
                    //                    msg.writer().flush();
                    //                    for(Player _p: p.zone.players) {
                    //                        _p.session.sendMessage(msg);
                    //                    }
                    //                    msg.cleanup();
                    //                } catch (Exception e) {
                    //                    e.printStackTrace();
                    //                } finally {
                    //                    if(msg != null) {
                    //                        msg.cleanup();
                    //                    }
                    //                }
                    break;
                }
                break;
            }
            // ba hat mit
            case 21: {
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        Message msg = null;
                        if (select == 0) { //ep sao trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n trang b???\n(??o, qu???n, g??ng, gi??y ho???c rada) c?? ?? ?????t\nsao pha l??\nCh???n lo???i sao pha l??\nSau ???? ch???n 'N??ng c???p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho trang b??? c???a ng????i\ntr??? n??n m???nh m???");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 1) { // pha lee hoa trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n trang b???\n(??o, qu???n, g??ng, gi??y ho???c rada)\n Sau ???? ch???n 'N??ng c???p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho trang b??? c???a ng????i\ntr??? th??nh trang b??? pha l??");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) { //xac nhan nang cap
                        Service.gI().sendEpStarItem(p);
                        break;
                    }
                    if (p.menuID == 1 && select == 0) { //xac nhan nang cap
                        Service.gI().sendUpStarItem(p);
                        break;
                    }
                    break;
                } else if (p.map.id == 42 || p.map.id == 43 || p.map.id == 44) {
                    if (p.menuID == -1) {
                        Message msg = null;
                        if (select == 0) { //mua buaf
                            doMenuArray(p, idNpc, "B??a c???a ta r???t l???i h???i. Mua xong c?? t??c d???ng ngay nh??, nh??? tranh th??? s??? d???ng, tho??t game ph?? l???m. Mua c??ng nhi???u th???i gian gi?? c??ng r???!", new String[]{"B??a D??ng 1\ngi???", "B??a D??ng 8\ngi???", "B??a D??ng 1\nth??ng"});
                        }
                        if (select == 1) { //nang cap trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n trang b???\n(??o, qu???n, g??ng, gi??y ho???c rada)\nCh???n lo???i ???? ????? n??ng c???p\n???? b???o v??? ?????t ??? v??? tr?? cu???i(n???u c??)\nSau ???? ch???n 'N??ng c???p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho trang b??? c???a ng????i\ntr??? n??n m???nh m???");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 2) { //lam phep nhap da
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n 10 m???nh ???? v???n\nCh???n m???t b??nh n?????c ph??p\nSau ???? ch???n 'L??m ph??p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho 10 m???nh ???? v???n\ntr??? th??nh 1 ???? n??ng c???p");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 3) { //Nhap ngoc rong
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n 7 vi??n ng???c c??ng sao\nSau ???? ch???n 'L??m ph??p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho 7 vi??n Ng???c R???ng\nth??nh 1 vi??n Ng???c R???ng c???p cao");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        } else if (select == 4) { //NANG CAP BONG TAI PORATA
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n b??ng tai Porata\nCh???n m???nh b??ng tai ????? n??ng c???p, s??? l?????ng\n??????9999 c??i\nSau ???? ch???n 'N??ng c???p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho b??ng tai Porata c???a ng????i\nth??nh c???p 2");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        } else if (select == 5) { //MO CHI SO BONG TAI PORATA2
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("V??o h??nh trang\nCh???n b??ng tai Porata\nCh???n m???nh h???n b??ng tai s??? l?????ng 99 c??i\nv?? ???? xanh lam ????? n??ng c???p\nSau ???? ch???n 'N??ng c???p'");
                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho b??ng tai Porata c???p 2 c???a ng????i\nc?? 1 ch??? s??? ng???u nhi??n");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
//                        else if (select == 6) {
//                            try {
//                                msg = new Message(-81);
//                                msg.writer().writeByte(0);
//                                msg.writer().writeUTF("V??o h??nh trang\nCh???n trang b??? h???y di???t\nCh???n m???nh trang b??? th???n, s??? l?????ng\n99 c??i\nSau ???? ch???n 'N??ng c???p'");
//                                msg.writer().writeUTF("Ta s??? ph?? ph??p\ncho trang b??? h???y di???t c???a ng????i\nth??nh trang b??? th???n Heart");
//                                msg.writer().flush();
//                                p.session.sendMessage(msg);
//                                msg.cleanup();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (msg != null) {
//                                    msg.cleanup();
//                                }
//                            }
//                            //                        doMenuArray(p,idNpc,"N??ng trang b??? Th???n Heart",new String[]{"N??ng\ntrang b???\nHeart","M??? ch??? s???\nHeart"});
//                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) { //bua 1 gio
                        TabItemShop[] test = Shop.getTabShop(21, 0).toArray(new TabItemShop[0]);
                        GameScr.UIshop(p, test);
                    } else if (p.menuID == 0 && select == 1) { // bua 8 gio
                        TabItemShop[] test = Shop.getTabShop(21, 1).toArray(new TabItemShop[1]);
                        GameScr.UIshop(p, test);
                    } else if (p.menuID == 0 && select == 2) { //bua 1 thang
                        TabItemShop[] test = Shop.getTabShop(21, 2).toArray(new TabItemShop[1]);
                        GameScr.UIshop(p, test);
                    } else if (p.menuID == 1 && select == 0) { //xac nhan nang cap item
                        Service.gI().sendUpLevelItem(p);
                        break;
                    } else if (p.menuID == 2 && select == 0) { //xac nhan ep da
                        Service.gI().sendEpDaVun(p);
                        break;
                    } else if (p.menuID == 3 && select == 0) { //xac nhan ep da
                        Service.gI().sendEpNgocRong(p);
                        break;
                    } else if (p.menuID == 4 && select == 0) { //xac nhan n??ng c???p
                        Service.gI().sendUpPorata(p);
                        break;
                    } else if (p.menuID == 5 && select == 0) { //xac nhan mo chi so bong tai
                        Service.gI().sendOpenOptionPorata(p);
                        break;
                    }
                    break;
                }
                //            if(p.menuID != -1)
                //            {
                //                if(p.menuID == 1 && select == 0)
                //                {
                //                    TabItemShop[] test = Shop.getTabShop(21, 0).toArray(new TabItemShop[0]);
                //                    GameScr.UIshop(p, test);
                //                }
                //            }
                //            if(select == 1){
                //                doMenuArray(p,idNpc,"B??a c???a ta r???t l???i h???i. Mua xong c?? t??c d???ng ngay nh??, nh??? tranh th??? s??? d???ng, tho??t game ph?? l???m. Mua c??ng nhi???u th???i gian gi?? c??ng r???!",new String[]{"B??a\n1 th??ng"});
                //                p.menuID = select;
                //            }
                //            break;
            }
            // ghi danh dai hoi vo thuat
            case 23: {
                if (p.map.id == 52) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.chatNPC(p, (short) p.menuNPCID, "L???ch thi ?????u trong ng??y\bGi???i Nhi ?????ng: 8,13,18h\bGi???i Si??u c???p 1: 9,14,19h\bGi???i Si??u c???p 2: 10,15,20h\bGi???i Si??u c???p 3: 11,16,21h\bGi???i Ngo???i h???ng: 12,17,22,23h\nGi???i th?????ng khi th???ng m???i v??ng\bGi???i Nhi ?????ng: 2 ng???c\bGi???i Si??u c???p 1: 4 ng???c\bGi???i Si??u c???p 2: 6 ng???c\bGi???i Si??u c???p 3: 8 ng???c\bGi???i Ngo???i h???ng: 10.000 v??ng\bV?? ?????ch: 5 vi??n ???? n??ng c???p\nVui l??ng ?????n ????ng gi??? ????? ????ng k?? thi ?????u");
                        }
                        if (select == 1) {
                            if (DaiHoiManager.gI().openDHVT && (System.currentTimeMillis() <= DaiHoiManager.gI().tOpenDHVT)) {
                                String nameDH = DaiHoiManager.gI().nameRoundDHVT();
                                doMenuArray(p, idNpc, "Hi???n ??ang c?? gi???i ?????u " + nameDH + " b???n c?? mu???n ????ng k?? kh??ng?", new String[]{"Gi???i\n" + nameDH + "\n(" + DaiHoiManager.gI().costRoundDHVT() + ")", "T??? ch???i"});
                            } else {
                                Service.chatNPC(p, (short) p.menuNPCID, "???? h???t th???i gian ????ng k??, h??y quay l???i ??? gi???i sau");
                                break;
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 1) {
                        if (select == 0) {
                            if (DaiHoiService.gI().canRegisDHVT(p.sumTiemNang)) {
                                if (DaiHoiManager.gI().lstIDPlayers.size() < 256) {
                                    if (DaiHoiManager.gI().typeDHVT == (byte) 5 && p.vang >= 10000) {
                                        if (DaiHoiManager.gI().isAssignDHVT(p.id)) {
                                            p.sendAddchatYellow("B???n ???? ????ng k?? tham gia ?????i h???i v?? thu???t r???i");
                                        } else {
                                            p.vang -= 10000;
                                            Service.gI().buyDone(p);
                                            Service.chatNPC(p, (short) p.menuNPCID, "B???n ???? ????ng k?? th??nh c??ng, nh??? c?? m???t t???i ????y tr?????c gi??? thi ?????u");
                                            //                    DaiHoiManager.gI().lstPlayers.add(p);
                                            DaiHoiManager.gI().lstIDPlayers.add(p.id);
                                        }
                                    } else if (DaiHoiManager.gI().typeDHVT > (byte) 0 && DaiHoiManager.gI().typeDHVT < (byte) 5 && p.ngoc >= (int) (2 * DaiHoiManager.gI().typeDHVT)) {
                                        if (DaiHoiManager.gI().isAssignDHVT(p.id)) {
                                            p.sendAddchatYellow("B???n ???? ????ng k?? tham gia ?????i h???i v?? thu???t r???i");
                                        } else {
                                            p.ngoc -= (int) (2 * DaiHoiManager.gI().typeDHVT);
                                            Service.gI().buyDone(p);
                                            Service.chatNPC(p, (short) p.menuNPCID, "B???n ???? ????ng k?? th??nh c??ng, nh??? c?? m???t t???i ????y tr?????c gi??? thi ?????u");
                                            //                    DaiHoiManager.gI().lstPlayers.add(p);
                                            DaiHoiManager.gI().lstIDPlayers.add(p.id);
                                        }
                                    } else {
                                        p.sendAddchatYellow("Kh??ng ????? v??ng ng???c ????? ????ng k?? thi ?????u");
                                    }
                                } else {
                                    p.sendAddchatYellow("Hi???n t???i ???? ?????t t???i s??? l?????ng ng?????i ????ng k?? t???i ??a, xin h??y ch??? ?????n gi???i sau");
                                }

                            } else {
                                p.sendAddchatYellow("B???n kh??ng ????? ??i???u ki???n tham gia gi???i n??y, h??y quay l???i v??o gi???i ph?? h???p");
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 20: { //KAIO
                if (p.map.id == 48) {
                    if (p.menuID != -1) {
                        if (p.menuID == 2 && select == 0) { //CON DUONG RAN DOC
                            p.zone.goMapTransport(p, 143);
                            break;
                        } else if (p.menuID == 2 && select == 1) {//HUONG DAN
                            Service.chatNPC(p, (short) p.menuNPCID, "Hi???n t???i, map ch??a ???????c ho??n thi???n dragon ??ang m??? ????? cho c??c b???n tr???i nghi???m!");
                            break;
                        }
                    }
                    if (select == 0) {
                        p.zone.goMapTransport(p, 45);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 50);
                    }
                    if (select == 2) {
                        doMenuArray(p, idNpc, "Con ???????ng r???n ?????c, l?? m???t n??i c???c k?? nguy hi???m theo nh?? ta th???y th?? con ch??a ????? tr??nh ????u !", new String[]{"Tham Gia", "H?????ng D???n"});
                        p.menuID = select;
                    } else if (select == 3) {
                        break;
                    }
                }
                break;
            }
            case 18: { //THAN MEO
                if (p.map.id == 46) {
                    if (p.menuID != -1) {
                    }
                    if (select == 0) {
                        Service.chatNPC(p, (short) p.menuNPCID, "dragon, l?? t??n m??y ch??? ch???c kh??ng c???n nh???c th?? con c??ng bi???t l?? trang ch??? dragon.vn nh??? ?");
                        p.menuID = select;
                    }
                }
                break;
            }
            case 19: { //THUONG DE
                if (p.map.id == 45) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 48);
                        }
                        if (select == 1) { //Open UI QUAY NGOC
                            if (p.ItemQuay.size() > 0) {
                                doMenuArray(p, idNpc, "B???n c?? th??? ch???n t??? 1 ?????n 7 vi??n ng???c, gi?? m???i vi??n l?? 4 ng???c\n??u ti??n d??ng v?? quay tr?????c", new String[]{"Top 100", "?????ng ??", "V??ng quay\n?????c bi???t", "R????ng ph???\n??ang c?? " + p.ItemQuay.size() + "\nm??n", "????ng"});
                            } else {
                                doMenuArray(p, idNpc, "B???n c?? th??? ch???n t??? 1 ?????n 7 vi??n ng???c, gi?? m???i vi??n l?? 4 ng???c\n??u ti??n d??ng v?? quay tr?????c", new String[]{"Top 100", "?????ng ??", "V??ng quay\n?????c bi???t", "????ng"});
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 1) {
                        if (select == 0) {
                            break;
                        } else if (select == 1) { //QUAY NGOC THUONG
                            LuckyService.gI().loadUILucky(p);
                        } else if (select == 2) { //QUAY NGOC DAC BIET
                            LuckyService.gI().loadUILucky(p);
                        }
                        if (p.ItemQuay.size() > 0) {
                            if (select == 3) {
                                p.openItemQuay = true;
                                LuckyService.gI().openItemQuay(p);
                            } else if (select == 4) {
                                break;
                            }
                        } else {
                            if (select == 3) {
                                break;
                            }
                        }
                    }
                    break;
                }
                if (p.map.id == 141) {
                    if (p.menuID != -1) {
                    }
                    if (select == 0) {
                        Service.chatNPC(p, (short) p.menuNPCID, "Hi???n t???i dragon ch??a ho??n thi???n xong map n??y vui l??ng quay l???i sau..!");
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 48);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            //        case 16:{
            //            if(select == 0){
            //                TabItemShop[] test = Shop.getTabShop(16, p.gender).toArray(new TabItemShop[0]);
            //                GameScr.UIshop(p, test);
            //                break;
            //            }
            //            break;
            //        }
            case 13: {
                if (p.map.id == 5) {
                    if (p.menuID != -1) {
                        if (p.menuID == 0 && select == 0) {
                            if (p.pointSuKien < 10) {
                                p.sendAddchatYellow("??i???m s??? ki???n c???a b???n kh??ng ?????");
                                return;
                            }
                            p.pointSuKien -= 10;
                            Item Item = ItemBuff.getItem(573);
                            p.addItemToBag(Item);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n v???a ?????i th??nh c??ng 1 Capsule B???c");
                        } else if (p.menuID == 0 && select == 1) {
                            if (p.pointSuKien < 100) {
                                p.sendAddchatYellow("??i???m s??? ki???n c???a b???n kh??ng ?????");
                                return;
                            }
                            p.pointSuKien -= 100;
                            Item Item = ItemBuff.getItem(574);
                            p.addItemToBag(Item);
                            Service.gI().updateItemBag(p);
                            Service.gI().buyDone(p);
                            p.sendAddchatYellow("B???n v???a ?????i th??nh c??ng 1 Capsule V??ng");
                            break;
                        } else if (p.menuID == 0 && select == 2) {
                            if (p.pointSuKien < 100) {
                                p.sendAddchatYellow("??i???m s??? ki???n c???a b???n kh??ng ?????");
                                return;
                            }
                            p.pointSuKien -= 100;
                            int tyle = Util.nextInt(0, 100);
                            int random = Util.nextInt(15, 21);
                            if (tyle > 1) {
                                Item ItemRanDom = ItemBuff.getItem(random);
                                p.addItemToBag(ItemRanDom);
                                p.sendAddchatYellow("Th??? v???n may nh???n ???????c ng???c r???ng " + (random - 13) + " Sao");
                            } else if (tyle <= 1) {
                                Item ItemRanDom2 = ItemBuff.getItem(12);
                                p.addItemToBag(ItemRanDom2);
                                p.sendAddchatYellow("Th??? v???n may nh???n ???????c ng???c r???ng 1 Sao");
                            }
                            Service.gI().buyDone(p);
                            Service.gI().updateItemBag(p);
                            break;
                        } else if (p.menuID == 1 && select == 0) {
                            //CHECK NHIEM VU VONG 2 DHVT
                            if (p.taskId == (short) 18 && p.crrTask.index == (byte) 1 && Server.gI().isPassDHVT) {
                                TaskService.gI().updateCountTask(p);
                            }
                            break;
                        }
                        if (p.menuID == 1 && select == 1) {
                            //CHECK NHIEM VU VONG 2 DHVT
                            if (p.taskId == (short) 19 && p.crrTask.index == (byte) 1) {
                                TaskService.gI().updateCountTask(p);
                            }
                            break;
                        }
                        if (p.menuID == 2 && select == 0) { //titan
                            Service.chatNPC(p, (short) 24, HelperDAO.getTopPower());
                            break;
                        } else if (p.menuID == 2 && select == 1) {//ruby
//                            Service.chatNPC(p, (short) 24, HelperDAO.getTopCard());
                            break;
                        } else if (p.menuID == 2 && select == 2) {//tat
                            //                        Item TITAN = ItemSell.getItemNotSell(224);
                            //                        p.addItemToBagx99(TITAN);
                            //                        Service.gI().updateItemBag(p);
                            //                        p.sendAddchatYellow("Nh???n th??nh c??ng ???? Th???ch Anh T??m");
                            break;
                        } else if (p.menuID == 2 && select == 3) {//saphia
                            //                        Item TITAN = ItemSell.getItemNotSell(221);
                            //                        p.addItemToBagx99(TITAN);
                            //                        Service.gI().updateItemBag(p);
                            //                        p.sendAddchatYellow("Nh???n th??nh c??ng ???? Saphia");
                            break;
                        } else if (p.menuID == 2 && select == 4) {//luc bao
                            //                        Item TITAN = ItemSell.getItemNotSell(220);
                            //                        p.addItemToBagx99(TITAN);
                            //                        Service.gI().updateItemBag(p);
                            //                        p.sendAddchatYellow("Nh???n th??nh c??ng ???? L???c B???o");
                            break;
                        } else if (p.menuID == 3 && select == 0) { //GIAI TAN BANG HOI
                            if (Server.gI().isServer == (byte) 1) {
                                ClanService.gI().distoryClan(p);
                            } else {
                                p.sendAddchatYellow("Ch??? th???c hi???n t???i dragon oke 01");
                            }
                            break;
                        } else if (p.menuID == 3 && select == 1) { //KHU VUC BANG HOI
                            GotoMap(p, 153);
                            break;
                        } else if (p.menuID == 4 && select == 0) { //KHO BAU DUOI BIEN
                            if (p.map.id == 5) {
                                p.zone.goMapTransport(p, 135);
                                break;
                            }
                        } else if (p.menuID == 4 && select == 1) { //HUONG DAN
                            Service.chatNPC(p, (short) p.menuNPCID, "Hi???n t???i kho b??u d?????i bi???n ch??a ho??n thi???n dragon ch??? m??? ????? cho c??c b???n tr???i nghi???m map n??y!");
                            break;
                        }
                    }
                    if (select == 0) {
                        doMenuArray(p, idNpc, "Theo ta th???y th?? con v???n c??n F.A\nSao kh??ng ??i ki???m g???u m?? c??n ??? nh?? ch??i game ?\b|1|Th??i ???????c r???i ????u c??ng F.A ?????c quy t???c s??? ki???n ??i\b|5|??i???m S??? Ki???n ??ang C??: " + p.pointSuKien + "\n|7|1 Capsule B???c = 10 ??i???m s??? ki???n\n|7|1 Capsule V??ng = 100 ??i???m s??? ki???n\n|7|1 l???n Th??? V???n May Ng???c R???ng = 100 ??i???m s??? ki???n\n", new String[]{"?????i\nCapsule B???c", "?????i\nCapsule V??ng", "Th??? V???n May\nNg???c R???ng"});
                        p.menuID = select;
                    }
                    if (select == 1) {
                        doMenuArray(p, idNpc, "Ch??o con, ta r???t vui khi g???p con\nCon mu???n l??m g?? n??o?", new String[]{"?????i H???i\nV?? Thu???t", "Trung\nU?? Tr???ng"});
                        p.menuID = select;
                    }
                    if (select == 2) {
                        doMenuArray(p, idNpc, "Ch??o con, ta r???t vui khi g???p con\nCon mu???n l??m g?? n??o?", new String[]{"S???c M???nh", "N???p Th???"});
                        p.menuID = select;
                    } else if (select == 3) {
                        doMenuArray(p, idNpc, "Ch??o con, ta r???t vui khi g???p con\nCon mu???n l??m g?? n??o?", new String[]{"Gi???i t??n\nbang h???i", "Khu v???c\nbang h???i"});
                        p.menuID = select;
                    } else if (select == 4) {
                        doMenuArray(p, idNpc, "Ch??o con, ta r???t vui khi g???p con\nCon mu???n l??m g?? n??o?", new String[]{"Kho B??u\nD?????i Bi???n", "H?????ng D???n"});
                        p.menuID = select;
                    }
                }
                break;
            }

            case 12: {//CUI DI CHUYEN
                if (p.map.id == 19) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 105);
                            //                        GotoMap(p,109);
                        }
                        if (select == 1) {
                            p.zone.goMapTransport(p, 68);
                            //                        GotoMap(p,68);
                        }
                        if (select == 2) {
                            break;
                        }
                        if (select == 3) {
                            if (p.taskId == (short) 21 && p.crrTask.index == (byte) 0) {
                                doMenuArray(p, idNpc, "Ta v???a th???y t??n Kuku, ng????i c?? mu???n t???i ch??? h???n kh??ng", new String[]{"OK\n100 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 1) {
                                doMenuArray(p, idNpc, "Ta v???a th???y t??n M???p ?????u ??inh, ng????i c?? mu???n t???i ch??? h???n kh??ng", new String[]{"OK\n100 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 2) {
                                doMenuArray(p, idNpc, "Ta v???a th???y t??n Rambo, ng????i c?? mu???n t???i ch??? h???n kh??ng", new String[]{"OK\n100 ng???c", "T??? ch???i"});
                                p.menuID = select;
                            }
                            break;
                        }
                    } else if (p.menuID == 3) {
                        if (select == 0 && p.taskId == (short) 21) {
                            if (p.crrTask.index == (byte) 0) {
                                if (Server.gI().mapKUKU != 0) {
                                    if (p.petfucus == 1) {
                                        p.zone.leaveDetu(p, p.detu);
                                    }
                                    if (p.pet2Follow == 1 && p.pet != null) {
                                        p.zone.leavePETTT(p.pet);
                                    }
                                    p.zone.leave(p);
                                    int _rdLocation = Util.nextInt(0, (Server.gI().maps[Server.gI().mapKUKU].template.arMobid.length - 1)); //get index mob random
                                    p.x = Server.gI().maps[Server.gI().mapKUKU].template.arrMobx[_rdLocation];
                                    p.y = Server.gI().maps[Server.gI().mapKUKU].template.arrMoby[_rdLocation];
                                    Server.gI().maps[Server.gI().mapKUKU].area[Server.gI().khuKUKU].EnterCapsule(p);
                                } else {
                                    Service.chatNPC(p, (short) p.menuNPCID, "Kuku ch??a xu???t hi???n");
                                }
                            } else if (p.crrTask.index == (byte) 1) {
                                if (Server.gI().mapMDD != 0) {
                                    if (p.petfucus == 1) {
                                        p.zone.leaveDetu(p, p.detu);
                                    }
                                    if (p.pet2Follow == 1 && p.pet != null) {
                                        p.zone.leavePETTT(p.pet);
                                    }
                                    p.zone.leave(p);
                                    int _rdLocation = Util.nextInt(0, (Server.gI().maps[Server.gI().mapMDD].template.arMobid.length - 1)); //get index mob random
                                    p.x = Server.gI().maps[Server.gI().mapMDD].template.arrMobx[_rdLocation];
                                    p.y = Server.gI().maps[Server.gI().mapMDD].template.arrMoby[_rdLocation];
                                    Server.gI().maps[Server.gI().mapMDD].area[Server.gI().khuMDD].EnterCapsule(p);
                                } else {
                                    Service.chatNPC(p, (short) p.menuNPCID, "M???p ?????u ??inh ch??a xu???t hi???n");
                                }
                            } else if (p.crrTask.index == (byte) 2) {
                                if (Server.gI().mapRAMBO != 0) {
                                    if (p.petfucus == 1) {
                                        p.zone.leaveDetu(p, p.detu);
                                    }
                                    if (p.pet2Follow == 1 && p.pet != null) {
                                        p.zone.leavePETTT(p.pet);
                                    }
                                    p.zone.leave(p);
                                    int _rdLocation = Util.nextInt(0, (Server.gI().maps[Server.gI().mapRAMBO].template.arMobid.length - 1)); //get index mob random
                                    p.x = Server.gI().maps[Server.gI().mapRAMBO].template.arrMobx[_rdLocation];
                                    p.y = Server.gI().maps[Server.gI().mapRAMBO].template.arrMoby[_rdLocation];
                                    Server.gI().maps[Server.gI().mapRAMBO].area[Server.gI().khuRAMBO].EnterCapsule(p);
                                } else {
                                    Service.chatNPC(p, (short) p.menuNPCID, "Rambo ch??a xu???t hi???n");
                                }
                            }
                        }
                        break;
                    }
                } else if (p.map.id == 68) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 19);
                    }
                    if (select == 1) {
                        break;
                    }
                } else if (p.map.id == 26) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 24);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 25);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            case 11: {
                if (p.map.id == 25) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 24);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 26);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            case 10: {
                if (p.map.id == 24) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 25);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 26);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            case 9: {
                if (p.map.id == 14) {
                    if (select == 0) {
                        TabItemShop[] test = Shop.getTabShop(9, 0).toArray(new TabItemShop[0]);
                        GameScr.UIshop(p, test);
                        break;
                    }
                }
                break;
            }
            case 8: { //DENDE
                if (p.map.id == 7) {
                    if (p.imgNRSD == (byte) 53) {
                        if (select == 1) {
                            if (p.nrNamec == 353) {
                                //                        if(Server.gI().firstNrNamec && p.zone.canCallDragonNamec(p)) {
                                //                            Server.gI().firstNrNamec = false;
                                //                            Server.gI().timeNrNamec = System.currentTimeMillis() + 600000;
                                //                            Service.chatNPC(p, (short)idNpc, "Ng???c b???n qu??, xin ch??? em 9 ph??t n???a ????? lau b??ng ng???c, g???i R???ng m???i hi???n linh");
                                //                            break;
                                //                        }
                                //                            if(!Server.gI().firstNrNamec) {
                                //                        if((Server.gI().timeNrNamec - System.currentTimeMillis()) > 0) {
                                //                            Service.chatNPC(p, (short)idNpc, "Ng???c b???n qu??, xin ch??? em "+ (int)((Server.gI().timeNrNamec - System.currentTimeMillis())/60000) +" ph??t n???a ????? lau b??ng ng???c, g???i R???ng m???i hi???n linh");
                                //                            break;
                                //                        } else { //GOI RONG NAMEC
                                if (p.zone.canCallDragonNamec(p)) {
                                    Server.gI().tOpenNrNamec = System.currentTimeMillis() + 86400000;
                                    Server.gI().firstNrNamec = true;
                                    Server.gI().timeNrNamec = 0;

                                    Service.gI().doneDragonNamec(p);
                                    //INIT NGOC RONG HOA THACH
                                    Service.gI().initNgocRongNamec((byte) 1);
                                    //TIMER TASK INIT LAI NGOC RONG NAMEC
                                    Service.gI().reInitNrNamec((long) 86399000);
                                    Service.gI().callDragonNamec(p);
                                    //UI CHON DIEU UOC NGOC RONG NAMEK
                                    p.menuID = -1;
                                    p.menuNPCID = 27;
                                    Menu.doMenuArray(p, 27, "Ta s??? ban cho c??? bang h???i c??c ng????i 1 ??i???u ?????c, ng????i c?? 5 ph??t, h??y\nsuy ngh?? th???t k??? tr?????c khi quy???t ?????nh", new String[]{"???? b???o v???\nx10", "Berry ??eo\nl??ng"});
                                    break;
                                }
                                //                        }
                                //                            break;
                                //                        } else {
                                //                               Service.chatNPC(p, (short)idNpc, "Ng???c b???n qu??, xin ch??? em 9 ph??t n???a ????? lau b??ng ng???c, g???i R???ng m???i hi???n linh");
                                //                            break;
                                //                            }
                            } else {
                                p.sendAddchatYellow("Anh ph???i c?? vi??n ng???c r???ng Nam???c 1 sao");
                            }
                        }
                    } else {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(8, 2).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                            break;
                        }
                    }
                }
                break;
            }
            case 7: {
                if (p.map.id == 0) {
                    if (select == 0) {
                        TabItemShop[] test = Shop.getTabShop(7, 1).toArray(new TabItemShop[0]);
                        GameScr.UIshop(p, test);
                        break;
                    }
                }
                break;
            }
            case 4: {
                if (p.map.id == 21 || p.map.id == 22 || p.map.id == 23) {
                    if (p.upMagicTree) { //NANG CAP DAU THAN
                        if (p.menuID == -1) {
                            if (select == 0) {
                                doMenuArray(p, idNpc, "B???n c?? ch???c ch???n mu???n n??ng c???p nhanh?", new String[]{"OK", "T??? ch???i"});
                            }
                            if (select == 1) {
                                doMenuArray(p, idNpc, "B???n c?? ch???c ch???n mu???n h???y n??ng c???p?", new String[]{"OK", "T??? ch???i"});
                            }
                            p.menuID = select;
                            break;
                        }
                        if (p.menuID == 0 && select == 0) {
                            if (p.ngoc >= ngocUpMagicTree(p.levelTree) && p.levelTree < (byte) 10) {
                                p.ngoc -= ngocUpMagicTree(p.levelTree);
                                Service.gI().buyDone(p);
                                p.levelTree = (byte) (p.levelTree + 1) > (byte) 10 ? (byte) 10 : (byte) (p.levelTree + 1);
                                p.upMagicTree = false;
                                p.maxBean = (byte) (p.maxBean + 2) > (byte) 23 ? (byte) 23 : (byte) (p.maxBean + 2);
                                p.lastTimeTree = System.currentTimeMillis();
                                Service.gI().MagicTree(p, (byte) 0);
                            } else {
                                p.sendAddchatYellow("Kh??ng ????? ng???c ????? n??ng c???p ?????u th???n");
                            }
                            break;
                        }
                        if (p.menuID == 1 && select == 0) {
                            p.vang += goldUpMagicTree(p.levelTree) / 2;
                            Service.gI().buyDone(p);
                            p.upMagicTree = false;
                            p.lastTimeTree = System.currentTimeMillis();
                            Service.gI().MagicTree(p, (byte) 0);
                            p.sendAddchatYellow("???? h???y n??ng c???p ?????u th???n");
                            break;
                        }
                    } else {
                        if (p.menuID == -1) {
                            if (select == 0) {
                                int countDAUHT = 0;
                                //COUNT DAU THAN TRONG HANH TRANG
                                for (byte i = 0; i < p.ItemBag.length; i++) {
                                    if (p.ItemBag[i] != null && p.ItemBag[i].template.type == (byte) 6) {
                                        countDAUHT += p.ItemBag[i].quantity;
                                    }
                                }
                                int maxDauTheo = 20;
                                if (p.gender == (byte) 1) {
                                    maxDauTheo = 30;
                                }
                                if (countDAUHT < maxDauTheo) {
                                    byte countThu = (byte) (maxDauTheo - countDAUHT) > p.maxBean ? p.maxBean : (byte) (maxDauTheo - countDAUHT);
                                    p.currentBean -= countThu;
                                    p.lastTimeTree = System.currentTimeMillis() - (long) (p.currentBean * p.levelTree * 60000);
                                    int itemDAUTHAN = 13;
                                    if (p.levelTree == (byte) 2) {
                                        itemDAUTHAN = 60;
                                    } else if (p.levelTree == (byte) 3) {
                                        itemDAUTHAN = 61;
                                    } else if (p.levelTree == (byte) 4) {
                                        itemDAUTHAN = 62;
                                    } else if (p.levelTree == (byte) 5) {
                                        itemDAUTHAN = 63;
                                    } else if (p.levelTree == (byte) 6) {
                                        itemDAUTHAN = 64;
                                    } else if (p.levelTree == (byte) 7) {
                                        itemDAUTHAN = 65;
                                    } else if (p.levelTree == (byte) 8) {
                                        itemDAUTHAN = 352;
                                    } else if (p.levelTree == (byte) 9) {
                                        itemDAUTHAN = 523;
                                    } else if (p.levelTree == (byte) 10) {
                                        itemDAUTHAN = 595;
                                    }
                                    byte indexDAU = p.getIndexBagid(itemDAUTHAN);
                                    if (indexDAU != -1) {
                                        p.ItemBag[indexDAU].quantity += (int) countThu;
                                    } else {
                                        byte indexNOT = p.getIndexBagNotItem();
                                        if (p.getBagNull() == 0) {
                                            p.sendAddchatYellow("H??nh trang kh??ng ????? ch??? tr???ng!");
                                            return;
                                        } else {
                                            ItemSell dauThan = ItemSell.getItemSell(itemDAUTHAN, (byte) 1);
                                            Item dauThuHoach = new Item(dauThan.item);
                                            dauThuHoach.quantity = (int) countThu;
                                            p.ItemBag[indexNOT] = dauThuHoach;
                                        }
                                    }
                                    Service.gI().updateItemBag(p);
                                    p.sendAddchatYellow("B???n v???a thu ho???ch th??nh c??ng " + countThu + " vi??n ?????u th???n c???p " + p.levelTree);
                                } else {
                                    p.sendAddchatYellow("S??? ?????u trong h??nh trang ???? ?????y");
                                }
                                Service.gI().MagicTree(p, (byte) 0);

                                p.getIndexItemBagByType((byte) 6); //GET DAU THAN TRONG HANH TRANG

                            }
                            if (p.levelTree >= (byte) 10) {
                                if (select == 1) { //THU DAU NHANH
                                    if (p.ngoc >= ngocThuDauThan(p.levelTree)) {
                                        p.ngoc -= ngocThuDauThan(p.levelTree);
                                        Service.gI().buyDone(p);
                                        p.currentBean = p.maxBean;
                                        p.lastTimeTree = 0;
                                        Service.gI().MagicTree(p, (byte) 0);
                                    } else {
                                        p.sendAddchatYellow("Kh??ng ????? ng???c ????? n??ng c???p ?????u th???n");
                                    }
                                }
                            } else {
                                if (select == 1) {
                                    doMenuArray(p, idNpc, "B???n c?? ch???c ch???n mu???n n??ng c???p ?????u th???n?", new String[]{"OK", "T??? ch???i"});
                                }
                                if (select == 2) { //THU DAU NHANH
                                    if (p.ngoc >= ngocThuDauThan(p.levelTree)) {
                                        p.ngoc -= ngocThuDauThan(p.levelTree);
                                        Service.gI().buyDone(p);
                                        p.currentBean = p.maxBean;
                                        p.lastTimeTree = 0;
                                        Service.gI().MagicTree(p, (byte) 0);
                                    } else {
                                        p.sendAddchatYellow("Kh??ng ????? ng???c ????? n??ng c???p ?????u th???n");
                                    }
                                }
                            }
                            p.menuID = select;
                            break;
                        }
                        if (p.levelTree < (byte) 10) {
                            if (p.menuID == 1) {
                                if (select == 0) { //NANG CAP DAU THAN
                                    if (p.vang >= goldUpMagicTree(p.levelTree) && p.levelTree < (byte) 10) {
                                        p.vang -= goldUpMagicTree(p.levelTree);
                                        Service.gI().buyDone(p);
                                        //                        p.levelTree += (byte)1;
                                        p.upMagicTree = true;
                                        p.lastTimeTree = System.currentTimeMillis() + (long) (timeUpMagicTree(p.levelTree) * 1000);
                                        Service.gI().MagicTree(p, (byte) 0);
                                    } else {
                                        p.sendAddchatYellow("Kh??ng ????? v??ng ????? n??ng c???p ?????u th???n");
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 2: {
                if (p.map.id == 22) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.chatNPC(p, (short) p.menuNPCID, "M???t c???u b?? c?? ??u??i kh??? ???????c t??m th???y b???i m???t ??ng l??o s???ng m???t m??nh trong r???ng, ??ng ?????t t??n l?? Son Goku v?? xem ?????a b?? nh?? l?? ch??u c???a m??nh. M???t ng??y n??? Goku t??nh c??? g???p m???t c?? g??i t??n l?? Bulma tr??n ???????ng ??i b???t c?? v???, Goku v?? Bulma ???? c??ng nhau truy t??m b???y vi??n ng???c r???ng. C??c vi??n ng???c r???ng n??y ch???a ?????ng m???t b?? m???t c?? th??? tri???u h???i m???t con r???ng v?? ban ??i???u ?????c cho ai s??? h???u ch??ng. Tr??n cu???c h??nh tr??nh d??i ??i t??m nh???ng vi??n ng???c r???ng, h??? g???p nh???ng ng?????i b???n (Yamcha, Krillin,Yajirobe, Thi??n, Gi??o t???, Oolong,...) v?? nh???ng ?????u s?? huy???n tho???i c??ng nh?? nhi???u ??c qu???. H??? tr???i qua nh???ng kh?? kh??n v?? h???c h???i c??c chi??u th???c v?? thu???t ?????c bi???t ????? tham gia thi ?????u trong ?????i h???i v?? thu???t th??? gi???i ???????c t??? ch???c h???ng n??m. Ngo??i c??c s??? ki???n ?????i h???i v?? thu???t, Goku v?? c??c b???n c??n ph???i ?????i ph?? v???i c??c th??? l???c ?????c ??c nh?? ?????i v????ng Pilaf, Qu??n ??o??n kh??n ????? c???a ?????c nh??n t?????ng qu??n, ?????i ma v????ng Picollo v?? nh???ng ?????a con c???a h???n. Chi???n binh ng?????i Saiya: Radiz, Ho??ng t??? Saiya Vegeta c??ng t??n c???n v??? Nappa. R???i h??? ??i ?????n Namek, g???p r???ng th???n c???a Namek; ch???m tr??n Frieza, khi tr??? v??? Tr??i ?????t ?????ng ????? Nh??m android s??t th??? (c??c Android 16, 17, 18,19, 20) v?? sau ???? l?? qu??i v???t t??? t????ng lai Cell, K??? th?? t??? v?? tr??? Majin Buu, th???n h???y di???t Beerus, c??c ?????i th??? t??? c??c v?? tr??? song song, ?????i th??? m???nh nh???t v???i Goku l?? Jiren (?????n t??? v?? tr??? 11).");
                            break;
                        }
                          if (select == 1) {
                              long _vang = ((long) p.vang + 500000000) > 2000000000L ? 2000000000L : ((long) p.vang + 500000000);
                              p.vang = _vang;
                              Service.gI().buyDone(p);
                              p.sendAddchatYellow("Nh???n th??nh c??ng 500 tri???u v??ng");
                              break;
                          }
                          if (select == 2) {
                              if (p.ngoc < 10000000) {
                                  p.ngoc = p.ngoc + 100000;
                                  Service.gI().buyDone(p);
                                  p.sendAddchatYellow("Nh???n th??nh c??ng 100k ng???c");
                              } else {
                                  p.sendAddchatYellow("Vui l??ng s??? d???ng h???t ng???c");
                              }
                              break;
                          }
                        if (p.hasTrungMabu && select == 1) {
                            doMenuArray(p, idNpc, "Con c?? mu???n nh???n ????? t??? Mab??, s??? thay th??? ????? t??? hi???n t???i n???u c??", new String[]{"OK", "X??a ?????\nMab??", "????ng"});
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.hasTrungMabu) {
                        if (p.menuID == 1) {
                            if (select == 0) {
                                p.statusPet = 0;
                                p.detu = null;
                                p.detu = new Detu();
                                p.detu.initDetuMabu(p.detu, p.gender);
                                p.detu.id = (-100000 - p.id);
                                p.hasTrungMabu = false;
                            } else if (select == 1) {
                                p.hasTrungMabu = false;
                            } else {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 1: {
                if (p.map.id == 23) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.chatNPC(p, (short) p.menuNPCID, "M???t c???u b?? c?? ??u??i kh??? ???????c t??m th???y b???i m???t ??ng l??o s???ng m???t m??nh trong r???ng, ??ng ?????t t??n l?? Son Goku v?? xem ?????a b?? nh?? l?? ch??u c???a m??nh. M???t ng??y n??? Goku t??nh c??? g???p m???t c?? g??i t??n l?? Bulma tr??n ???????ng ??i b???t c?? v???, Goku v?? Bulma ???? c??ng nhau truy t??m b???y vi??n ng???c r???ng. C??c vi??n ng???c r???ng n??y ch???a ?????ng m???t b?? m???t c?? th??? tri???u h???i m???t con r???ng v?? ban ??i???u ?????c cho ai s??? h???u ch??ng. Tr??n cu???c h??nh tr??nh d??i ??i t??m nh???ng vi??n ng???c r???ng, h??? g???p nh???ng ng?????i b???n (Yamcha, Krillin,Yajirobe, Thi??n, Gi??o t???, Oolong,...) v?? nh???ng ?????u s?? huy???n tho???i c??ng nh?? nhi???u ??c qu???. H??? tr???i qua nh???ng kh?? kh??n v?? h???c h???i c??c chi??u th???c v?? thu???t ?????c bi???t ????? tham gia thi ?????u trong ?????i h???i v?? thu???t th??? gi???i ???????c t??? ch???c h???ng n??m. Ngo??i c??c s??? ki???n ?????i h???i v?? thu???t, Goku v?? c??c b???n c??n ph???i ?????i ph?? v???i c??c th??? l???c ?????c ??c nh?? ?????i v????ng Pilaf, Qu??n ??o??n kh??n ????? c???a ?????c nh??n t?????ng qu??n, ?????i ma v????ng Picollo v?? nh???ng ?????a con c???a h???n. Chi???n binh ng?????i Saiya: Radiz, Ho??ng t??? Saiya Vegeta c??ng t??n c???n v??? Nappa. R???i h??? ??i ?????n Namek, g???p r???ng th???n c???a Namek; ch???m tr??n Frieza, khi tr??? v??? Tr??i ?????t ?????ng ????? Nh??m android s??t th??? (c??c Android 16, 17, 18,19, 20) v?? sau ???? l?? qu??i v???t t??? t????ng lai Cell, K??? th?? t??? v?? tr??? Majin Buu, th???n h???y di???t Beerus, c??c ?????i th??? t??? c??c v?? tr??? song song, ?????i th??? m???nh nh???t v???i Goku l?? Jiren (?????n t??? v?? tr??? 11).");
                            break;
                        }
//                        if (select == 1) {
//                            long _vang = ((long) p.vang + 500000000) > 2000000000L ? 2000000000L : ((long) p.vang + 500000000);
//                            p.vang = _vang;
//                            Service.gI().buyDone(p);
//                            p.sendAddchatYellow("Nh???n th??nh c??ng 500 tri???u v??ng");
//                            break;
//                        }
//                        if (select == 2) {
//                            if (p.ngoc < 10000000) {
//                                p.ngoc = p.ngoc + 100000;
//                                Service.gI().buyDone(p);
//                                p.sendAddchatYellow("Nh???n th??nh c??ng 100k ng???c");
//                            } else {
//                                p.sendAddchatYellow("Vui l??ng s??? d???ng h???t ng???c");
//                            }
//                            break;
//                        }
                        if (p.hasTrungMabu && select == 1) {
                            doMenuArray(p, idNpc, "Con c?? mu???n nh???n ????? t??? Mab??, s??? thay th??? ????? t??? hi???n t???i n???u c??", new String[]{"OK", "X??a ?????\nMab??", "????ng"});
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.hasTrungMabu) {
                        if (p.menuID == 1) {
                            if (select == 0) {
                                p.statusPet = 0;
                                p.detu = null;
                                p.detu = new Detu();
                                p.detu.initDetuMabu(p.detu, p.gender);
                                p.detu.id = (-100000 - p.id);
                                p.hasTrungMabu = false;
                            } else if (select == 1) {
                                p.hasTrungMabu = false;
                            } else {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 0: {
                if (p.map.id == 21) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.chatNPC(p, (short) p.menuNPCID, "M???t c???u b?? c?? ??u??i kh??? ???????c t??m th???y b???i m???t ??ng l??o s???ng m???t m??nh trong r???ng, ??ng ?????t t??n l?? Son Goku v?? xem ?????a b?? nh?? l?? ch??u c???a m??nh. M???t ng??y n??? Goku t??nh c??? g???p m???t c?? g??i t??n l?? Bulma tr??n ???????ng ??i b???t c?? v???, Goku v?? Bulma ???? c??ng nhau truy t??m b???y vi??n ng???c r???ng. C??c vi??n ng???c r???ng n??y ch???a ?????ng m???t b?? m???t c?? th??? tri???u h???i m???t con r???ng v?? ban ??i???u ?????c cho ai s??? h???u ch??ng. Tr??n cu???c h??nh tr??nh d??i ??i t??m nh???ng vi??n ng???c r???ng, h??? g???p nh???ng ng?????i b???n (Yamcha, Krillin,Yajirobe, Thi??n, Gi??o t???, Oolong,...) v?? nh???ng ?????u s?? huy???n tho???i c??ng nh?? nhi???u ??c qu???. H??? tr???i qua nh???ng kh?? kh??n v?? h???c h???i c??c chi??u th???c v?? thu???t ?????c bi???t ????? tham gia thi ?????u trong ?????i h???i v?? thu???t th??? gi???i ???????c t??? ch???c h???ng n??m. Ngo??i c??c s??? ki???n ?????i h???i v?? thu???t, Goku v?? c??c b???n c??n ph???i ?????i ph?? v???i c??c th??? l???c ?????c ??c nh?? ?????i v????ng Pilaf, Qu??n ??o??n kh??n ????? c???a ?????c nh??n t?????ng qu??n, ?????i ma v????ng Picollo v?? nh???ng ?????a con c???a h???n. Chi???n binh ng?????i Saiya: Radiz, Ho??ng t??? Saiya Vegeta c??ng t??n c???n v??? Nappa. R???i h??? ??i ?????n Namek, g???p r???ng th???n c???a Namek; ch???m tr??n Frieza, khi tr??? v??? Tr??i ?????t ?????ng ????? Nh??m android s??t th??? (c??c Android 16, 17, 18,19, 20) v?? sau ???? l?? qu??i v???t t??? t????ng lai Cell, K??? th?? t??? v?? tr??? Majin Buu, th???n h???y di???t Beerus, c??c ?????i th??? t??? c??c v?? tr??? song song, ?????i th??? m???nh nh???t v???i Goku l?? Jiren (?????n t??? v?? tr??? 11).");
                            //Service.gI().clientInput(p, "Nh???p M?? Qu?? T???ng", "M?? Qu?? T???ng", (byte) 0);
                            break;
                        }
//                        if (select == 1) {
//                            long _vang = ((long) p.vang + 500000000) > 2000000000L ? 2000000000L : ((long) p.vang + 500000000);
//                            p.vang = _vang;
//                            Service.gI().buyDone(p);
//                            p.sendAddchatYellow("Nh???n th??nh c??ng 500 tri???u v??ng");
//                            break;
//                        }
//                        if (select == 2) {
//                            if (p.ngoc < 10000000) {
//                                p.ngoc = p.ngoc + 100000;
//                                Service.gI().buyDone(p);
//                                p.sendAddchatYellow("Nh???n th??nh c??ng 100k ng???c");
//                            } else {
//                                p.sendAddchatYellow("Vui l??ng s??? d???ng h???t ng???c");
//                            }
//                            break;
//                        }
                        if (p.hasTrungMabu && select == 1) {
                            doMenuArray(p, idNpc, "Con c?? mu???n nh???n ????? t??? Mab??, s??? thay th??? ????? t??? hi???n t???i n???u c??", new String[]{"OK", "X??a ?????\nMab??", "????ng"});
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.hasTrungMabu) {
                        if (p.menuID == 1) {
                            if (select == 0) {
                                p.statusPet = 0;
                                p.detu = null;
                                p.detu = new Detu();
                                p.detu.initDetuMabu(p.detu, p.gender);
                                p.detu.id = (-100000 - p.id);
                                p.hasTrungMabu = false;
                            } else if (select == 1) {
                                p.hasTrungMabu = false;
                            } else {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            default: {
                Service.gI().sendTB(p.session, 0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idNpc, 0);
                break;
            }
        }
        m.cleanup();
    }

    public void GotoMap(Player p, int id) {
        Map maptele = MainManager.getMapid(id);
        Controller.getInstance().teleportToMAP(p, maptele);
    }

    public void menuHandler(Player p, Message m) throws IOException, SQLException, InterruptedException {
        byte idNPC = m.reader().readByte();// ID NPC
        byte menuID = m.reader().readByte();// L???p n??t 1
        byte select = m.reader().readByte();// L???p n??t 2
//         System.out.println("menuID: "+ p.menuID);
//         System.out.println("menuNPCID: "+ p.menuNPCID);
//         System.out.println("select: "+ select);
        int tl;
        switch (p.menuNPCID) {

            case 13:
                if (p.menuID == 1) {
                    if (select == 0) {
                        p.openBox();
                    }
                }
                break;

            default: {
                Service.gI().sendTB(p.session, 0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idNPC, 0);
                break;
            }

            //   Service.getInstance().serverMessage(p.session,"ID NPC " + b1);
        }
        m.cleanup();
    }

    public void openUINpc(Player p, Message m) throws IOException {
        short idnpc = m.reader().readShort();//idnpc
        int avatar;
        m.cleanup();
        p.menuID = -1;
        p.menuNPCID = idnpc;
        avatar = NpcAvatar(p, idnpc);
        m = new Message(33);

        if (p.menuNPCID == 74 && p.map.id == 5) {
            doMenuArray(p, idnpc, "Ch??o m???ng ?????n v???i c???a h??ng c???a ta", new String[]{"OK", "T??? ch???i"});
            return;
        } else if (p.menuNPCID == 73) {
            doMenuArray(p, idnpc, "?????i password", new String[]{"OK", "T??? ch???i"});
            return;
        } else if (p.menuNPCID == 72 && p.map.id == 160) {
            doMenuArray(p, idnpc, "Ch??o m???ng ?????n v???i c???a h??ng c???a ta, s??? d???ng 10 huy ch????ng ?????ng v?? 1000 ng???c ????? ?????i nh???ng m??n ????? gi?? tr??? v???i ch??? s??? ng???u nhi??n l??n t???i 30% (Thu th???p huy ch????ng ?????ng b???ng c??ch ti??u di???t Black Goku ho???c Cooler)", new String[]{"OK", "T??? ch???i"});
            return;
        } else if (p.menuNPCID == 71 && p.map.id == 161) {
            if (p.taskId == (short) 31) {
                if (p.crrTask.index == (byte) 5 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "C???u s??? ????a t??i v??? ch??? Bardock th???t sao?");
                    return;
                }
            } else if (p.taskId == (short) 32) {
                if (p.crrTask.index == (byte) 2 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    Item thuocMo = ItemSell.getItemNotSell(1016);
                    Item _thuocMo = new Item(thuocMo);
                    p.addItemToBag(_thuocMo);
                    Service.gI().updateItemBag(p);
                    p.sendAddchatYellow("B???n nh???n ???????c Thu???c m??? Ipana");
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "C???u ???? ch???m tr??n t??n ???? r???i sao?");
                    return;
                }
            }
            Service.chatNPC(p, (short) p.menuNPCID, "??i ??u ????a ??i...");
            return;
        } else if (p.menuNPCID == 70 && p.map.id == 160) { //BARDOCK
            if (p.taskId == (short) 31) {
                if (p.crrTask.index == (byte) 2 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "T??i t??n l?? Bardock, ng?????i Xayda\bH??nh tinh c???a t??i v???a b??? Fide ph?? h???y\bKh??ng bi???t t???i sao t??i tho??t ch???t...\bv?? xu???t hi???n t???i n??i n??y n???a\bT??i ??ang b??? th????ng, c???u h??y gi??p t??i h??? ????m l??nh ngo??i kia");
                    return;
                } else if (p.crrTask.index == (byte) 4 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "C???m ??n c???u\bGi??? nh??? c???u ??i t??m nh??c Berry v??? gi??p t??i\bc?? th??? c???u nh??c loanh quanh ??? B??? R???ng Nguy??n Th???y");
                    return;
                } else if (p.crrTask.index == (byte) 6 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "M??n c???u l???n n???a\bHi???n t???i trong hang kh??ng c??n g?? ????? ??n\bC???u h??y gi??p t??i t??m m???t ??t l????ng th???c");
                    return;
                } else if (p.crrTask.index == (byte) 7 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    for (byte i = 0; i < p.ItemBag.length; i++) {
                        if (p.ItemBag[i] != null && p.ItemBag[i].template.id == 993 && (p.ItemBag[i].quantity == 99)) {
                            p.ItemBag[i] = null;
                            TaskService.gI().updateCountTask(p);
                            Service.gI().updateItemBag(p);
                            Service.chatNPC(p, (short) p.menuNPCID, "M??n c???u th??m l???n n???a\bV???i s??? l????ng th???c n??y t??i s??? s???m b??nh ph???c\bNgo??i kia b???n l??nh ??ang ???c hi???p c?? d??n h??nh tinh n??y\bMong c???u c?? th??? ra s???c l???n n???a ????? c???u h???");
                            return;
                        }
                    }
                    Service.chatNPC(p, (short) p.menuNPCID, "C???u thu th???p 99 gi??? th???c ??n ????? d??? tr???");
                    return;
                } else if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            }
            Service.chatNPC(p, (short) p.menuNPCID, "S??ng ra su???i, t???i v??o hang...");
            return;
        } else if (p.menuNPCID == 67 && p.map.id == 0) { //MR POPO
            doMenuArray(p, idnpc, "Th?????ng ????? v???a ph??t hi???n 1 lo???i kh?? ??ang ??m th???m\nh???y di???t m???i m???m s???ng tr??n Tr??i ?????t,\nn?? ???????c g???i l?? Destron Gas.\nTa s??? ????a c??c c???u ?????n n??i ???y, c??c c???u s???n s??ng ch??a?", new String[]{"Th??ng tin\nChi ti???t", "Top 100\nBang h???i", "Th??nh t??ch\nBang", "OK", "T??? ch???i"});
            return;
        } else if (p.menuNPCID == 61) {//DOI YARDART
            doMenuArray(p, idnpc, "H??y c??? g???ng t???p luy???n\nThu th???p 9.999 b?? ki???p ????? ?????i trang ph???c Yardrat nh??", new String[]{"Nh???n th?????ng", "OK"});
            return;
        } else if (p.menuNPCID == 60 && (p.map.id == 80 || p.map.id == 131)) { //GOKU NUI KHI VANG
            doMenuArray(p, idnpc, "Ta m???i h??? Fide, nh??ng n?? ???? k???p ????o 1 c??i l???\nH??nh tinh n??y s???p n??? tinh r???i\nMau l?????n th??i", new String[]{"Chu???n"});
            return;
        } else if (p.menuNPCID == 56) {
            if (p.map.id == 154) {
                doMenuArray(p, idnpc, "Th??? ????nh v???i ta xem n??o.\nNg????i c??n 1 l?????t n???a c?? m??.", new String[]{"N??i chuy???n", "Top 100", "[LV:1]"});
            }
            return;
        } else if (p.menuNPCID == 55) { //THAN HUY DIET BILL
            if (p.map.id == 48) {
                doMenuArray(p, idnpc, "Kh??ng mu???n h??nh tinh n??y b??? h???y di???t th?? mang 99 ph???n ????? ??n t???i ????y,\nta s??? cho m???t m??n ????? H???y Di???t.\nPh???c v??? t???t ta c?? th??? cho trang b??? m???nh m??? h??n ?????n 15%", new String[]{"?????ng ??", "T??? ch???i"});
            } else if (p.map.id == 154) {
                doMenuArray(p, idnpc, "...", new String[]{"V???\nTh??nh ?????a\nKaio", "T??? ch???i"});
            }
            return;
        } else if (p.menuNPCID == 54) { //TAPION
            if (p.map.id == 5) {
                doMenuArray(p, idnpc, "Ch??? s???p ??i c?????p ng??n h??ng em c?? mu???n ??i c??ng kh??ng ?", new String[]{"?????i ????? T???", "T??? ch???i"});
            }
            return;
        } else if (p.menuNPCID == 53) { //TAPION
            if (p.map.id == 19) {
                doMenuArray(p, idnpc, "??c qu??? truy???n thuy???t Hirudegarn\n???? tho??t kh???i phong ???n ng??n n??m\nH??y gi??p t??i ch??? ng??? n??", new String[]{"OK", "T??? ch???i"});
            } else if (p.map.id == 126) {
                doMenuArray(p, idnpc, "Ng????i mu???n b??? ch???y sao?", new String[]{"OK", "T??? ch???i"});
            }
            return;

        } else if (p.menuNPCID == 52) { //HUNG VUONG
            if (p.map.id == 0) {
                doMenuArray(p, idnpc, "T???m th???i ta s??? ??? ????y b??n c???i trang cho c??c c???u", new String[]{"C???a h??ng"});
            }
            return;
        } else if (p.menuNPCID == 48) { //NGO KHONG NGU HANH SON
            if (p.map.id == 122) {
                doMenuArray(p, idnpc, "H??y gi???i phong ???n cho ta, ta s??? t???ng ng????i m???t m??n qu??", new String[]{"C???a h??ng"});
            }
            return;
        } else if (p.menuNPCID == 49) { //DUONG TANG
            if (p.map.id == 0) {
                doMenuArray(p, idnpc, "A mi ph?? ph??, th?? ch??? h??y gi??p gi???i c???u ????? ????? c???a b???n t??ng ??ang b???\nphong ???n t???i ng?? h??nh s??n.", new String[]{"?????ng ??", "T??? ch???i", "Nh???n th?????ng"});
            } else if (p.map.id == 123) {
                doMenuArray(p, idnpc, "Th?? ch??? mu???n tr??? v??? sao ?", new String[]{"?????ng ??", "T??? ch???i"});
            } else if (p.map.id == 122) {
                doMenuArray(p, idnpc, "A mi ph?? ph??, th?? ch??? h??y thu th???p b??a 'gi???i khai phong ???n', m???i ch??? 10 c??i.", new String[]{"Gi???i\nPhong ???n", "V???\nL??ng Aru", "Top\nHoa qu???"});
            }
            return;
        } else if (p.menuNPCID == 47 && p.map.id == 153) { //NGUU MA VUONG
            doMenuArray(p, idnpc, "Ng????i ??ang mu???n t??m m???nh v??? v?? m???nh h???n b??ng tai Porata trong\ntruy???n thuy???t, ta s??? ????a ng????i ?????n ???? ?", new String[]{"OK", "????ng"});
            return;
        } else if (p.menuNPCID == 46) { //BABIDAY
            if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                if (p.cPk == (byte) 11) {
                    doMenuArray(p, idnpc, "B???n Kaio do con nh??c ??sin c???m ?????u ???? c?? m???t t???i ????y...H??y chu???n b??? 'Ti???p\nkh??ch' nh??!", new String[]{"H?????ng\nd???n\nth??m", "Gi???i tr???\nph??p thu???t\n1 ng???c", "Xu???ng\nT???ng d?????i", "V??? nh??"});
                } else {
                    doMenuArray(p, idnpc, "Haha...", new String[]{"OK"});
                }
            }
            return;
        } else if (p.menuNPCID == 44) { //OSSIN
            if (p.taskId == (short) 30) {
                if (p.crrTask.index == (byte) 0 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "C???u h??y chu???n b??? ????? ??i c??ng ch??ng t??i");
                    return;
                } else if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            }
            if (p.map.id == 50) {
                doMenuArray(p, idnpc, "Ta c?? th??? gi??p g?? ng????i?", new String[]{"?????n Kaio", "?????n\nh??nh tinh\nBill", "T??? ch???i"});
            } else if (p.map.id == 154) {
                doMenuArray(p, idnpc, "Ta c?? th??? gi??p g?? ng????i?", new String[]{"C???a h??ng", "?????n\nh??nh tinh\nNg???c t??", "T??? ch???i"});
            } else if (p.map.id == 155) {
                doMenuArray(p, idnpc, "Ta c?? th??? gi??p g?? ng????i?", new String[]{"?????n\nh??nh tinh\nBill", "T??? ch???i"});
            } else if (p.map.id == 52) {
                doMenuArray(p, idnpc, "B??y gi??? t??i s??? b?? m???t...\n??u???i theo 2 t??n ????? t???...\nQu?? v??? n??o mu???n ??i theo th?? xin m???i!", new String[]{"OK", "T??? ch???i"});
            } else if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                if (p.cPk == (byte) 10) {
                    doMenuArray(p, idnpc, "?????ng v???i xem th?????ng Babi????y, ngay ?????n cha h???n l?? th???n ma ?????o s??\nBibi????y khi c??n s???ng c??ng ph???i s??? h???n ?????y!", new String[]{"H?????ng\nd???n\nth??m", "Gi???i tr???\nph??p thu???t\n1 ng???c", "Xu???ng\nT???ng d?????i", "V??? nh??"});
                } else {
                    doMenuArray(p, idnpc, "Haha...", new String[]{"OK"});
                }
            }
            return;
        } else if (p.menuNPCID == 41) { //TRUNG THU
            if (p.map.id == 14) {
                doMenuArray(p, idnpc, "Trung thu ?????n r???i, xin ch??o!", new String[]{"C???a h??ng"});
            }
            return;
        } else if ((p.menuNPCID <= 36 && p.menuNPCID >= 30) && (p.map.id >= 85 && p.map.id <= 91)) { //NGOC RONG SAO DEN
            doMenuArray(p, idnpc, "Ta c?? th??? gi??p g?? cho ng?????i?", new String[]{"Ph?? h???", "T??? ch???i"});
            return;
        } else if (p.menuNPCID == 29 && (p.map.id == 24 || p.map.id == 25 || p.map.id == 26)) { //NGOC RONG SAO DEN
            if (p.indexNRSD.size() > 0) {
                doMenuArray(p, idnpc, "???????ng ?????n ng???c r???ng sao ??en ???? m???, ng????i c?? mu???n tham gia kh??ng?", new String[]{"H?????ng\nd???n\nth??m", "Nh???n th?????ng", "Tham gia", "T??? ch???i"});
            } else {
                doMenuArray(p, idnpc, "???????ng ?????n ng???c r???ng sao ??en ???? m???, ng????i c?? mu???n tham gia kh??ng?", new String[]{"H?????ng\nd???n\nth??m", "Tham gia", "T??? ch???i"});
            }
            return;
        } else if (p.menuNPCID == 23 && p.map.id == 52) {  //GHI DANH DAI HOI VO THUAT
            //???? h???t h???n ????ng k?? thi ?????u, xin vui l??ng ch??? ?????n gi???i sau v??o l??c 15h
            if (DaiHoiManager.gI().openDHVT) {
                String nameDH = DaiHoiManager.gI().nameRoundDHVT();
                doMenuArray(p, idnpc, "Ch??o m???ng b???n ?????n v???i ?????i h???i v?? thu???t\bGi???i " + nameDH + " ??ang c?? " + DaiHoiManager.gI().lstIDPlayers.size() + " ng?????i ????ng k?? thi ?????u", new String[]{"Th??ng tin\bChi ti???t", "????ng k??", "Gi???i\nSi??u H???ng", "?????i H???i\nV?? Thu???t\nL???n th???\n23"});
            } else {
                doMenuArray(p, idnpc, "???? h???t h???n ????ng k?? thi ?????u, xin vui l??ng ch??? ?????n gi???i sau", new String[]{"Th??ng tin\bChi ti???t", "OK", "Gi???i\nSi??u H???ng", "?????i H???i\nV?? Thu???t\nL???n th???\n23"});
            }
            return;
        } else if (p.menuNPCID == 21) {
            if (p.map.id == 5) {
                doMenuArray(p, idnpc, "Ng????i t??m ta c?? vi???c g???", new String[]{"??p sao trang\nb???", "Pha l?? h??a\ntrang b???", "Chuy???n h??a Trang\nb???", "V?? ????i Sinh\nT???"});
            } else if (p.map.id == 42 || p.map.id == 43 || p.map.id == 44) {
                doMenuArray(p, idnpc, "Ng????i t??m ta c?? vi???c g???", new String[]{"C???a h??ng\nB??a", "N??ng c???p V???t\n ph???m", "L??m ph??p\nNh???p ????", "Nh???p\nNg???c R???ng", "N??ng c???p\nB??ng tai\nPorata", "M??? ch??? s???\nB??ng tai\nPorata c???p 2"});
            }
            return;
        } else if (p.menuNPCID == 20 && p.map.id == 48) {  //THAN KAIO
            doMenuArray(p, idnpc, "Con mu???n quay v??? sao?", new String[]{"V??? Th???n ??i???n", "Th??nh ?????a\nKaio", "Con ???????ng\nR???n ?????c", "T??? Ch???i"});
            return;
        } else if (p.menuNPCID == 18 && p.map.id == 46) {  //THAN MEO
            doMenuArray(p, idnpc, "admin t???ng b???n n???t", new String[]{"N??i Chuy???n", "T??? Ch???i"});
            return;
        } else if (p.menuNPCID == 19 && p.map.id == 141) {
            doMenuArray(p, idnpc, "Gi???i l???m ti??u di???t ???????c ????m qu??i v???t ???? r???i ??, Ta c??n nhi???m v??? kh??c cho con ????y !", new String[]{"?????n Ch???\nCa????c", "V???\nTh??nh ?????a\nKaio", "T??? Ch???i"});
            return;
        } else if (p.menuNPCID == 19 && p.map.id == 45) {
            doMenuArray(p, idnpc, "Con ???? m???nh h??n ta, ta s??? ch??? ???????ng cho con ?????n Kaio ????? g???p th???n V??\nTr??? Ph????ng B???c\nNg??i l?? th???n cai qu???n v?? tr??? n??y, h??y theo ng??i ???y h???c v?? c??ng", new String[]{"?????n Kaio", "Quay ng???c\nMay m???n"});
            return;
        } else if (p.menuNPCID == 10 && p.map.id == 24) {
            doMenuArray(p, idnpc, "T??u V?? Tr??? c???a t??i c?? th??? ????a c???u ?????n h??nh tinh kh??c trong 3 gi??y. C???u mu???n ??i ????u", new String[]{"?????n\nNam???c", "?????n\nXayda", "T??? Ch???i"});
            return;
        } else if (p.menuNPCID == 11 && p.map.id == 25) {
            doMenuArray(p, idnpc, "T??u V?? Tr??? Nam???c tuy c?? nh??ng t???c ????? kh??ng h??? k??m b???t k??? lo???i t???u n??o kh??c. C???u mu???n ??i ????u?", new String[]{"?????n\nTr??i ?????t", "?????n\nXayda", "T??? Ch???i"});
            return;
        } else if (p.menuNPCID == 12) {
            if (p.map.id == 19) {
                if (p.taskId == (short) 21 && p.crrTask.index == (byte) 0) {
                    doMenuArray(p, idnpc, "?????i qu??n Fide ??ang ??? Thung L??ng Nappa, ta s??? ????a ng????i ?????n ????", new String[]{"?????n\nCold", "?????n\nNappa", "T??? Ch???i", "?????n ch???\nKuku"});
                } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 1) {
                    doMenuArray(p, idnpc, "?????i qu??n Fide ??ang ??? Thung L??ng Nappa, ta s??? ????a ng????i ?????n ????", new String[]{"?????n\nCold", "?????n\nNappa", "T??? Ch???i", "?????n ch???\nM???p ?????u ??inh"});
                } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 2) {
                    doMenuArray(p, idnpc, "?????i qu??n Fide ??ang ??? Thung L??ng Nappa, ta s??? ????a ng????i ?????n ????", new String[]{"?????n\nCold", "?????n\nNappa", "T??? Ch???i", "?????n ch???\nRambo"});
                } else {
                    doMenuArray(p, idnpc, "?????i qu??n Fide ??ang ??? Thung L??ng Nappa, ta s??? ????a ng????i ?????n ????", new String[]{"?????n\nCold", "?????n\nNappa", "T??? Ch???i"});
                }
            } else if (p.map.id == 68) {
                doMenuArray(p, idnpc, "Ng????i mu???n b??? ch???y ???", new String[]{"?????ng ??", "T??? Ch???i"});
            } else if (p.map.id == 26) {
                doMenuArray(p, idnpc, "T??u v?? tr??? Xayda s??? d???ng c??ng ngh??? m???i nh???t, c?? th??? ????a ng????i ??i b???t k??? ????u, ch??? c???n tr??? ti???n l?? ???????c", new String[]{"?????n\nTr??i ?????t", "?????n\nNam???c", "T??? Ch???i"});
            }
            return;
        } else if (p.menuNPCID == 42 && p.map.id == 43) { //Quoc vuong mo gioi han suc manh
            doMenuArray(p, idnpc, "Con mu???n n??ng gi???i h???n s???c m???nh cho b???n th??n hay ????? t????", new String[]{"B???n th??n", "????? t???", "T??? Ch???i"});
            return;
        } else if (p.menuNPCID == 38 && (p.map.id == 27 || p.map.id == 102)) { //TRUNKS SANG TUONG LAI
            if (p.taskId >= (short) 23) {
                if (p.taskId == (short) 24 && p.crrTask.index == (byte) 1 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Ch??u ?????n t??? t????ng lai v?? ch??u c???n c??c ch?? gi??p. ????y l?? thu???c tr??? tim cho Quy L??o, kh??ng l??u n???a Quy L??o s??? b??? b???nh.");
                    return;
                }
                if (p.map.id != 102) {
                    doMenuArray(p, idnpc, "Ch??o ch?? ch??u c?? th??? gi??p g???", new String[]{"??i ?????n T????ng lai", "T??? Ch???i"});
                    return;
                } else {
                    doMenuArray(p, idnpc, "Ch??o ch?? ch??u c?? th??? gi??p g???", new String[]{"Quay v???\nQu?? kh???", "T??? Ch???i"});
                    return;
                }
            } else {
                p.sendAddchatYellow("Ph???i ho??n th??nh nhi???m v??? tr?????c khi t???i ????y");
                Service.gI().buyDone(p);
            }
            return;
        } else if (p.menuNPCID == 37 && p.map.id == 102) { //BUNMA TUONG LAI
            if (p.taskId == (short) 24 && p.crrTask.index == (byte) 3 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                TaskService.gI().updateCountTask(p);
                Service.chatNPC(p, (short) p.menuNPCID, "C???m ??n c???u ???? ?????n ????y, ch??ng t??i ??ang g???p r???c r???i l???n. H??y gi??p ch??ng t??i ti??u di???t l?? X??n g???n ????y");
                return;
            } else {
                if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            }
            doMenuArray(p, idnpc, "C???m ??n b???n ???? ?????n ????y ????? gi??p ch??ng t??i", new String[]{"K??? Chuy???n", "C???a h??ng"});
            return;
        } else if (p.menuNPCID == 16 && (p.map.id == 24 || p.map.id == 25 || p.map.id == 26)) {
            TabItemShop[] test = Shop.getTabShop(16, p.gender).toArray(new TabItemShop[0]);
            GameScr.UIshop(p, test);
//                doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"C???a H??ng"});
            return;
        } else if (p.menuNPCID == 25 && p.map.id == 27) {
            if (p.clan != null) {
                if (p.clan.openDoanhTrai) {
                    doMenuArray(p, idnpc, "Doanh tr???i ?????c Nh??n ??ang ???????c m???,\nng????i c?? ch???c ch???n mu???n v??o tr???i ?????c Nh??n (c??n " + (30 - (int) ((System.currentTimeMillis() - p.clan.topen) / 60000)) + " ph??t)", new String[]{"OK", "T??? Ch???i"});
                } else {
                    doMenuArray(p, idnpc, "Ng????i c?? ch???c ch???n mu???n v??o tr???i ?????c Nh??n", new String[]{"OK", "T??? Ch???i"});
                }
            } else {
                doMenuArray(p, idnpc, "Vui l??ng gia nh???p bang h???i", new String[]{"OK", "T??? Ch???i"});
            }
            return;
        } else if (p.menuNPCID == 1 || p.menuNPCID == 0 || p.menuNPCID == 2) {
            if ((p.menuNPCID == 0 && p.map.id == 21) || (p.menuNPCID == 1 && p.map.id == 23) || (p.menuNPCID == 2 && p.map.id == 22)) {
                if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    long _vang = ((long) p.vang + 700000000) > 2000000000L ? 2000000000L : ((long) p.vang + 700000000);
                    p.vang = _vang;
                    p.ngoc += 17000;
                    p.ngocKhoa += 34000;
                    Service.gI().buyDone(p);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh v?? ng???c, ng???c kho??, v??ng");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 4) {
                        TaskService.gI().setupNextNewTask(p, (byte) 7);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                } else if (p.taskId == (short) 8 && p.crrTask.index == (byte) 2) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Con ???? nghe v??? r???ng th???n ch??a, th??? c?? th??? th???c hi???n ???????c ??i???u ?????c. G???n ????y c?? th??p Karin, con h??y ?????n ???? xem x??t t??nh h??nh");
                    return;
                } else if (p.taskId == (short) 24 && p.crrTask.index == (byte) 0) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "OK, ta bi???t r???i. B??y gi??? con h??y ??i t??m v??? kh??ch l??? ????");
                    return;
                }
                if (p.hasTrungMabu) {
                    doMenuArray(p, idnpc, Text.get(0, 0), new String[]{"K??? chuy???n", "????? T???\nMab??"});
                } else {
                    doMenuArray(p, idnpc, Text.get(0, 0), new String[]{"K??? chuy???n"});
                }
            }
            return;
        } else if (p.menuNPCID == 39 && p.map.id == 5) {
            doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"C???a H??ng", "Ch???c N??ng", "?????i Ti???n", "Nh???n Qu??\nGi??ng Sinh"});
            return;
        } else if (p.menuNPCID == 9 && p.map.id == 14) {
            if (p.gender == 2) {
                if (p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID && p.crrTask.index == (byte) 2 && p.taskId == (short) 7) {
                    Service.chatNPC(p, (short) p.menuNPCID, "C???m ??n ng????i ???? c???u ta. Ta s??? s???n s??ng ph???c v??? n???u ng????i c???n mua v???t d???ng");
                    TaskService.gI().updateCountTask(p);
                    return;
                }
                doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"C???a H??ng"});
            } else {
                Service.gI().sendTB(p.session, 0, "Ta ch??? b??n ????? cho h??nh tinh Xayda", 0);
            }
            return;
        } else if (p.menuNPCID == 7 && p.map.id == 0) {
            if (p.gender == 0) {
                if (p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID && p.crrTask.index == (byte) 2 && p.taskId == (short) 7) {
                    Service.chatNPC(p, (short) p.menuNPCID, "C???m ??n ng????i ???? c???u ta. Ta s??? s???n s??ng ph???c v??? n???u ng????i c???n mua v???t d???ng");
                    TaskService.gI().updateCountTask(p);
                    return;
                }
                doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"C???a H??ng"});
            } else {
                Service.gI().sendTB(p.session, 0, "Ta ch??? b??n ????? cho h??nh tinh Tr??i ?????t", 0);
            }
            return;
        } else if (p.menuNPCID == 8 && p.map.id == 7) {
            if (p.imgNRSD == (byte) 53) {
                if (p.map.id == 7) {
                    doMenuArray(p, idnpc, "???, ng???c r???ng nam???c, b???n th???t l?? may m???n\nn???u t??m ????? 7 vi??n s??? ???????c R???ng Thi??ng Nam???c ban cho ??i???u ?????c", new String[]{"H?????ng\nd???n\nG???i R???ng", "G???i r???ng", "T??? ch???i"});
                }
            } else {
                if (p.gender == 1) {
                    if (p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID && p.crrTask.index == (byte) 2 && p.taskId == (short) 7) {
                        Service.chatNPC(p, (short) p.menuNPCID, "C???m ??n ng????i ???? c???u ta. Ta s??? s???n s??ng ph???c v??? n???u ng????i c???n mua v???t d???ng");
                        TaskService.gI().updateCountTask(p);
                        return;
                    }
                    doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"C???a H??ng"});

                } else {
                    Service.gI().sendTB(p.session, 0, "Ta ch??? b??n ????? cho h??nh tinh Nam???c", 0);
                }
            }
            return;
        } else if (p.menuNPCID == 13 && p.map.id == 5) { //QUY LAO
            if (p.taskId == (short) 12) {
//                if(p.crrTask.index == (byte)(p.crrTask.countSub - (byte)1) && p.crrTask.subtasks[(byte)(p.crrTask.countSub - (byte)1)] == (byte)p.menuNPCID && p.clan != null
//                && p.clan.members.size() >= 5 && p.gender == (byte)0) {
                if (p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1) && p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.gender == (byte) 0) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            } else if (p.taskId == (short) 24) {
                if (p.crrTask.index == (byte) 2 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Ta kh??ng bi???t chuy???n g?? s???p x???y ra nh??ng c???m ??n con");
                    return;
                }
            } else {
                if (((p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1))
                        || (p.taskId == (short) 9 && p.crrTask.index == (byte) 0)) && p.gender == (byte) 0) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 9) {
                        TaskService.gI().setupNextNewTask(p, (byte) 12);
                    } else if (p.taskId == (short) 17) {
                        TaskService.gI().setupNextNewTask(p, (byte) 20);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                }
            }
//            doMenuArray(p,idnpc,"Ch??o con, ta r???t vui khi g???p con\nCon mu???n l??m g?? n??o?",new String[]{"N??i chuy???n","T??nh n??ng","???? n??ng c???p","Bang h???i"});
            doMenuArray(p, idnpc, "Ch??o con, ta r???t vui khi g???p con\nCon mu???n l??m g?? n??o?", new String[]{"S??? Ki???n\nChristmas", "Nhi???m V???", "X???p H???ng", "Bang h???i", "Kho B??u\nD?????i Bi???n"});
            return;
        } else if (p.menuNPCID == 14 && p.map.id == 13) { //TRUONG LAO GURU
            if (p.taskId == (short) 12) {
//                if(p.crrTask.index == (byte)(p.crrTask.countSub - (byte)1) && p.crrTask.subtasks[(byte)(p.crrTask.countSub - (byte)1)] == (byte)p.menuNPCID && p.clan != null
//                && p.clan.members.size() >= 5 && p.gender == (byte)1) {
                if (p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1) && p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.gender == (byte) 1) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            } else {
                if (((p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1))
                        || (p.taskId == (short) 9 && p.crrTask.index == (byte) 0)) && p.gender == (byte) 1) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 9) {
                        TaskService.gI().setupNextNewTask(p, (byte) 12);
                    } else if (p.taskId == (short) 17) {
                        TaskService.gI().setupNextNewTask(p, (byte) 20);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                }
            }
            Service.gI().sendTB(p.session, 0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idnpc, 0);
            return;
        } else if (p.menuNPCID == 15 && p.map.id == 20) { //VUA VEGETA
            if (p.taskId == (short) 12) {
//                if(p.crrTask.index == (byte)(p.crrTask.countSub - (byte)1) && p.crrTask.subtasks[(byte)(p.crrTask.countSub - (byte)1)] == (byte)p.menuNPCID && p.clan != null
//                && p.clan.members.size() >= 5 && p.gender == (byte)2) {
                if (p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1) && p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.gender == (byte) 2) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            } else {
                if (((p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1))
                        || (p.taskId == (short) 9 && p.crrTask.index == (byte) 0)) && p.gender == (byte) 2) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("B???n v???a ???????c th?????ng " + Util.powerToString(p.crrTask.bonus) + " s???c m???nh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 9) {
                        TaskService.gI().setupNextNewTask(p, (byte) 12);
                    } else if (p.taskId == (short) 17) {
                        TaskService.gI().setupNextNewTask(p, (byte) 20);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                }
            }
            Service.gI().sendTB(p.session, 0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idnpc, 0);
            return;
        } else if (p.menuNPCID == 18 && p.map.id == 46) { //THAN MEO
            if (p.taskId == (short) 29 && p.crrTask.index == (byte) 0 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                if (p.damGoc >= 10000) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Con h??y ti???p t???c ?????n t????ng lai v?? thu th???p Capsule k?? b??");
                    return;
                } else {
                    Service.gI().buyDone(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Con h??y n??ng s???c ????nh g???c l??n 10K r???i quay l???i g???p ta");
                    return;
                }
            }
            return;
        } else if (p.menuNPCID == 4 && (p.map.id == 21 || p.map.id == 22 || p.map.id == 23)) {
            if (p.upMagicTree) {
                doMenuArray(p, idnpc, "C??y ?????u th???n", new String[]{"N??ng c???p\nnhanh " + ngocUpMagicTree(p.levelTree) + "\nng???c", "H???y n??ng\nc???p h???i " + Util.powerToString((long) (goldUpMagicTree(p.levelTree) / 2)) + "\nv??ng"});
            } else {
                if (p.levelTree >= (byte) 10) {
                    if (p.currentBean < p.maxBean) {
                        doMenuArray(p, idnpc, "C??y ?????u th???n", new String[]{"Thu ho???ch", "Thu ?????u nhanh\n" + ngocThuDauThan(p.levelTree) + " ng???c"});
                    } else {
                        doMenuArray(p, idnpc, "C??y ?????u th???n", new String[]{"Thu ho???ch"});
                    }
                } else {
                    if (p.currentBean < p.maxBean) {
                        doMenuArray(p, idnpc, "C??y ?????u th???n", new String[]{"Thu ho???ch", "N??ng c???p\n" + timeStringUpMagicTree(p.levelTree) + " " + Util.powerToString((long) goldUpMagicTree(p.levelTree)) + "\nv??ng", "Thu ?????u nhanh\n" + ngocThuDauThan(p.levelTree) + " ng???c"});
                    } else {
                        doMenuArray(p, idnpc, "C??y ?????u th???n", new String[]{"Thu ho???ch", "N??ng c???p\n" + timeStringUpMagicTree(p.levelTree) + " " + Util.powerToString((long) goldUpMagicTree(p.levelTree)) + "\nv??ng"});
                    }
                }
            }
            return;
        } else if (p.menuNPCID == 3) {
            p.openBox();
            return;
        } else {
            Service.gI().sendTB(p.session, 0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idnpc, 0);
        }
        m.writer().flush();
        p.session.sendMessage(m);
        m.cleanup();
    }

    public int NpcAvatar(Player p, int npcID) {

        for (int i = 0; i < p.getPlace().map.template.npcs.length; i++) {
            if (p.getPlace().map.template.npcs[i].tempId == npcID) {
                return p.getPlace().map.template.npcs[i].avartar;
            }

        }
        return -1;
    }

    public int vangMoNoiTai(byte count) {
        if (count == 0) {
            return 0;
        } else if (count == 1) {
            return 10;
        } else if (count == 2) {
            return 20;
        } else if (count == 3) {
            return 40;
        } else if (count == 4) {
            return 80;
        } else if (count == 5) {
            return 160;
        } else if (count == 6) {
            return 320;
        } else if (count == 7) {
            return 640;
        } else {
            return 1280;
        }
//        return 1280;
    }

    //vang nang cap dau than
    public int goldUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return 5000;
        } else if (level == (byte) 2) {
            return 10000;
        } else if (level == (byte) 3) {
            return 100000;
        } else if (level == (byte) 4) {
            return 1000000;
        } else if (level == (byte) 5) {
            return 10000000;
        } else if (level == (byte) 6) {
            return 20000000;
        } else if (level == (byte) 7) {
            return 50000000;
        } else if (level == (byte) 8) {
            return 100000000;
        } else if (level == (byte) 9) {
            return 300000000;
        }
        return 300000000;
    }

    //ngoc nang cap NHANH CAY DAU THAN
    public int ngocUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return 10;
        } else if (level == (byte) 2) {
            return 100;
        } else if (level == (byte) 3) {
            return 1000;
        } else if (level == (byte) 4) {
            return 3000;
        } else if (level == (byte) 5) {
            return 5000;
        } else if (level == (byte) 6) {
            return 7000;
        } else if (level == (byte) 7) {
            return 8000;
        } else if (level == (byte) 8) {
            return 9000;
        } else if (level == (byte) 9) {
            return 10000;
        }
        return 10000;
    }

    //NGOC THU HOACH NHANH DAU THAN
    public int ngocThuDauThan(byte level) {
        if (level == (byte) 1) {
            return 2;
        } else if (level == (byte) 2) {
            return 5;
        } else if (level == (byte) 3) {
            return 8;
        } else if (level == (byte) 4) {
            return 11;
        } else if (level == (byte) 5) {
            return 14;
        } else if (level == (byte) 6) {
            return 17;
        } else if (level == (byte) 7) {
            return 20;
        } else if (level == (byte) 8) {
            return 23;
        } else if (level == (byte) 9) {
            return 26;
        }
        return 26;
    }

    //TIME NANG CAP DAU THAN
    public int timeUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return 600;
        } else if (level == (byte) 2) {
            return 6000;
        } else if (level == (byte) 3) {
            return 58920;
        } else if (level == (byte) 4) {
            return 597600;
        } else if (level == (byte) 5) {
            return 1202400;
        } else if (level == (byte) 6) {
            return 2592000;
        } else if (level == (byte) 7) {
            return 4752000;
        } else if (level == (byte) 8) {
            return 5961600;
        } else if (level == (byte) 9) {
            return 8640000;
        }
        return 8640000;
    }

    //TIME STRING UP DAU THAN
    public String timeStringUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return "10m";
        } else if (level == (byte) 2) {
            return "1h40m";
        } else if (level == (byte) 3) {
            return "16h22m";
        } else if (level == (byte) 4) {
            return "6d22h";
        } else if (level == (byte) 5) {
            return "13d22h";
        } else if (level == (byte) 6) {
            return "30d";
        } else if (level == (byte) 7) {
            return "55d";
        } else if (level == (byte) 8) {
            return "69d";
        } else if (level == (byte) 9) {
            return "100d";
        }
        return "100d";
    }
}
