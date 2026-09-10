package com.nandhurealmoney.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

private val Navy = Color(0xFF20264D)
private val Blue = Color(0xFF1677FF)
private val Cyan = Color(0xFF16B7D2)
private val Orange = Color(0xFFFF7A18)
private val LightBlue = Color(0xFFF0F7FF)
private val Page = Color(0xFFF7F8FA)
private val Gray = Color(0xFF777777)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NandhuRealMoneyApp() }
    }
}

@Composable
fun NandhuRealMoneyApp() {
    MaterialTheme(colorScheme = lightColorScheme(primary = Blue, secondary = Orange, background = Page)) {
        val nav = rememberNavController()
        val entry by nav.currentBackStackEntryAsState()
        val route = entry?.destination?.route ?: "home"
        Scaffold(
            containerColor = Page,
            bottomBar = {
                if (route in listOf("home","orders","buy","upi","profile")) BottomNav(nav, route)
            }
        ) { padding ->
            NavHost(nav, "home", Modifier.padding(padding)) {
                composable("home") { Home(nav) }
                composable("orders") { Orders() }
                composable("buy") { Trade(nav, true) }
                composable("upi") { Upi() }
                composable("profile") { Profile(nav) }
                composable("introduction") { Introduction(nav) }
                composable("activity") { ActivityScreen() }
            }
        }
    }
}

@Composable
fun BrandHeader() {
    Row(
        Modifier.fillMaxWidth().background(Color.White).padding(start=27.dp, end=22.dp, top=18.dp, bottom=18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Nandhu", color = Orange, fontSize = 27.sp, fontWeight = FontWeight.Bold)
        Text("REAL", color = Blue, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.weight(1f))
        Surface(shape = CircleShape, color = LightBlue, modifier = Modifier.size(40.dp)) {
            Icon(Icons.Default.Person, null, tint = Blue, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun Home(nav: NavHostController) {
    LazyColumn(Modifier.fillMaxSize().background(Color.White)) {
        item { BrandHeader() }
        item {
            Spacer(Modifier.height(2.dp))
            Box(
                Modifier.padding(horizontal=20.dp).fillMaxWidth().height(188.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Brush.horizontalGradient(listOf(Blue, Cyan)))
            ) {
                Column(Modifier.padding(28.dp)) {
                    Text("Start your earning journey", color=Color.White, fontSize=25.sp, fontWeight=FontWeight.Bold)
                    Text("on NandhuReal Money!", color=Color.White, fontSize=25.sp, fontWeight=FontWeight.Bold)
                    Spacer(Modifier.height(14.dp))
                    Button(
                        onClick={nav.navigate("introduction")},
                        shape=RoundedCornerShape(9.dp),
                        colors=ButtonDefaults.buttonColors(containerColor=Color.White)
                    ) { Text("Learn more >", color=Blue, fontSize=17.sp, fontWeight=FontWeight.SemiBold) }
                }
                Row(Modifier.align(Alignment.BottomCenter).padding(bottom=10.dp), horizontalArrangement=Arrangement.spacedBy(7.dp)) {
                    Box(Modifier.width(27.dp).height(8.dp).clip(RoundedCornerShape(8.dp)).background(Color.White))
                    Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0x99FFFFFF)))
                    Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0x99FFFFFF)))
                }
            }
        }
        item {
            Column(Modifier.padding(start=37.dp,end=27.dp,top=29.dp)) {
                Text("My Balance  ", color=Navy, fontSize=20.sp)
                Row(verticalAlignment=Alignment.Bottom) {
                    Text("₹2,013.95", color=Navy, fontSize=39.sp, fontWeight=FontWeight.Medium)
                    Spacer(Modifier.width(8.dp))
                    Text("INR", color=Navy, fontSize=18.sp, modifier=Modifier.padding(bottom=7.dp))
                }
                Spacer(Modifier.height(18.dp))
                Card(
                    colors=CardDefaults.cardColors(containerColor=LightBlue),
                    shape=RoundedCornerShape(12.dp),
                    modifier=Modifier.fillMaxWidth()
                ) {
                    Row(Modifier.fillMaxWidth().padding(horizontal=20.dp, vertical=17.dp), horizontalArrangement=Arrangement.SpaceBetween) {
                        Row {
                            Text("In Sell UPI Count: ", color=Gray, fontSize=17.sp)
                            Text("5", color=Navy, fontSize=17.sp, fontWeight=FontWeight.Bold)
                        }
                        Text("›", color=Color.Gray, fontSize=27.sp, lineHeight=18.sp)
                    }
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth().padding(top=25.dp,bottom=27.dp), horizontalArrangement=Arrangement.SpaceEvenly) {
                HomeAction("Buy", Icons.Default.AddCard, Color(0xFFFF5570)) { nav.navigate("buy") }
                HomeAction("Sell", Icons.Default.Upload, Color(0xFFFFA34D)) { nav.navigate("buy") }
                HomeAction("+UPI", Icons.Default.CreditCard, Color(0xFF55B9F2)) { nav.navigate("upi") }
                HomeAction("Activity", Icons.Default.CardGiftcard, Color(0xFFA34DFF)) { nav.navigate("activity") }
            }
        }
        item {
            Column(Modifier.background(Page).padding(top=13.dp)) {
                Card(
                    Modifier.padding(26.dp).fillMaxWidth(),
                    colors=CardDefaults.cardColors(Color.White),
                    shape=RoundedCornerShape(0.dp)
                ) {
                    Column(Modifier.padding(8.dp, 18.dp, 8.dp, 8.dp)) {
                        Row(verticalAlignment=Alignment.CenterVertically) {
                            Surface(shape=RoundedCornerShape(14.dp), color=Color(0xFFEAF4FF), modifier=Modifier.size(72.dp)) {
                                Icon(Icons.Default.Star, null, tint=Blue, modifier=Modifier.padding(16.dp))
                            }
                            Spacer(Modifier.width(18.dp))
                            Column {
                                Text("Standard Member", color=Navy, fontSize=22.sp, fontWeight=FontWeight.Bold)
                                Text("Commission rate: 3.5%", color=Gray, fontSize=17.sp)
                            }
                        }
                        Spacer(Modifier.height(28.dp))
                        LinearProgressIndicator(progress={0.08f}, modifier=Modifier.fillMaxWidth().height(13.dp), trackColor=Color(0xFFF2F2F2))
                        Spacer(Modifier.height(28.dp))
                        Row(Modifier.fillMaxWidth(), verticalAlignment=Alignment.CenterVertically) {
                            Text("Buy ₹29,73,744 more to unlock ", color=Color(0xFF222222), fontSize=16.sp)
                            Text("Premium Member", color=Orange, fontSize=16.sp, fontWeight=FontWeight.Bold)
                            Spacer(Modifier.weight(1f))
                            Text("›", color=Orange, fontSize=28.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeAction(label:String, icon:androidx.compose.ui.graphics.vector.ImageVector, bg:Color, onClick:()->Unit) {
    Column(horizontalAlignment=Alignment.CenterHorizontally, modifier=Modifier.clickable{onClick()}.width(78.dp)) {
        Surface(shape=RoundedCornerShape(18.dp), color=bg, modifier=Modifier.size(64.dp)) {
            Icon(icon, null, tint=Color.White, modifier=Modifier.padding(17.dp))
        }
        Spacer(Modifier.height(7.dp))
        Text(label, color=Navy, fontSize=15.sp)
    }
}

@Composable
fun BottomNav(nav:NavHostController, route:String) {
    NavigationBar(containerColor=Color.White) {
        val items=listOf(
            Triple("home","Home",Icons.Default.Home),
            Triple("orders","Orders",Icons.Default.ReceiptLong),
            Triple("buy","Buy",Icons.Default.CurrencyRupee),
            Triple("upi","UPI",Icons.Default.CreditCard),
            Triple("profile","My",Icons.Default.Person)
        )
        items.forEach { (r,label,icon) ->
            NavigationBarItem(
                selected=route==r,
                onClick={nav.navigate(r){launchSingleTop=true}},
                icon={Icon(icon,null)},
                label={Text(label)},
                colors=NavigationBarItemDefaults.colors(selectedIconColor=Blue,selectedTextColor=Blue,indicatorColor=Color.Transparent)
            )
        }
    }
}

@Composable fun Orders() = SimplePage("Orders") { EmptyState("No orders yet") }

@Composable fun ActivityScreen() = SimplePage("Activity") { EmptyState("No activity yet") }

@Composable fun Upi() = SimplePage("+UPI Account") {
    Text("Wallet Working Mode",color=Navy,fontSize=22.sp,fontWeight=FontWeight.Bold)
    Spacer(Modifier.height(8.dp))
    Text("Demo interface only. No real UPI account is connected.",color=Gray,fontSize=16.sp)
    Spacer(Modifier.height(20.dp))
    listOf("Mobikwik","Freecharge","Navi").forEach { wallet ->
        Card(Modifier.fillMaxWidth().padding(bottom=10.dp), colors=CardDefaults.cardColors(Color.White)) {
            Row(Modifier.padding(18.dp),verticalAlignment=Alignment.CenterVertically) {
                Surface(Modifier.size(45.dp),shape=CircleShape,color=LightBlue){Icon(Icons.Default.AccountBalanceWallet,null,tint=Blue,modifier=Modifier.padding(10.dp))}
                Spacer(Modifier.width(15.dp)); Text(wallet,color=Navy,fontSize=18.sp,fontWeight=FontWeight.SemiBold)
            }
        }
    }
}

@Composable fun Trade(nav:NavHostController, buy:Boolean) {
    var amount by remember{mutableStateOf("")}
    var result by remember{mutableStateOf("")}
    Column(Modifier.fillMaxSize().background(Page).padding(24.dp)) {
        Text(if(buy)"Buy PCoin" else "Sell PCoin",color=Navy,fontSize=30.sp,fontWeight=FontWeight.Bold)
        Text("Demo transaction screen",color=Gray)
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(amount,{amount=it},label={Text("Amount in ₹")},singleLine=true,modifier=Modifier.fillMaxWidth())
        Spacer(Modifier.height(14.dp))
        Card(colors=CardDefaults.cardColors(LightBlue),shape=RoundedCornerShape(12.dp),modifier=Modifier.fillMaxWidth()){
            Text(if(buy)"Demo rate: 3.5% promotional calculation" else "Demo conversion: 1 PCoin = ₹1",color=Navy,modifier=Modifier.padding(18.dp))
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick={
            val a=amount.toDoubleOrNull()?:0.0
            result=if(a>0 && buy)"Demo result: ₹%.2f → %.2f PCoin".format(a,a*1.035)
            else if(a>0)"Demo result: %.2f PCoin → ₹%.2f".format(a,a)
            else "Please enter a valid amount."
        },modifier=Modifier.fillMaxWidth(),shape=RoundedCornerShape(10.dp)){Text(if(buy)"BUY (DEMO)" else "SELL (DEMO)")}
        if(result.isNotEmpty()){Spacer(Modifier.height(18.dp));Text(result,color=Navy,fontWeight=FontWeight.Bold)}
        Spacer(Modifier.height(18.dp))
        Text("This app does not perform real-money transfers.",color=Gray,fontSize=14.sp)
    }
}

@Composable fun Profile(nav:NavHostController)=SimplePage("My") {
    Text("NandhuReal Money",color=Navy,fontSize=23.sp,fontWeight=FontWeight.Bold)
    Spacer(Modifier.height(15.dp))
    Button(onClick={nav.navigate("introduction")}){Text("Introduction")}
}

@Composable fun Introduction(nav:NavHostController) {
    LazyColumn(Modifier.fillMaxSize().background(Color.White).padding(horizontal=34.dp)) {
        item {
            Spacer(Modifier.height(26.dp))
            Text("NandhuReal Money",color=Navy,fontSize=32.sp,fontWeight=FontWeight.Bold,textAlign=TextAlign.Center,modifier=Modifier.fillMaxWidth())
            Text("Introduction",color=Gray,fontSize=17.sp,textAlign=TextAlign.Center,modifier=Modifier.fillMaxWidth())
            Spacer(Modifier.height(30.dp))
        }
        item{Section("What is PCoin?","PCoin is a demo token used inside NandhuReal Money. In this project it has no cash value and cannot be withdrawn.") }
        item{Section(""Buy" means?","This screen demonstrates buying demo PCoin with a rupee amount. The example calculation uses 3.5% as shown in the reference interface.") }
        item{Section(""Sell" means?","This screen demonstrates converting demo PCoin into a displayed rupee value. No bank or UPI transfer is performed.") }
        item{Section("How you earn?","The app demonstrates percentage calculations for UI purposes. It does not promise income, profit, or investment returns.") }
        item{Section("+UPI Account","The UPI section is a visual demo. It does not request bank credentials or connect to payment providers.") }
        item{Section("Advantages of Wallet Working Mode","• Simple wallet-style interface\n• No bank credentials required\n• No real-money transfers\n• Buy/Sell demo screens\n• Activity and membership UI") }
    }
}

@Composable fun Section(title:String,body:String) {
    Column(Modifier.padding(bottom=30.dp)) {
        Row(verticalAlignment=Alignment.CenterVertically) {
            Box(Modifier.width(6.dp).height(35.dp).background(Blue))
            Spacer(Modifier.width(12.dp))
            Text(title,color=Color(0xFF31445A),fontSize=24.sp,fontWeight=FontWeight.Bold)
        }
        Spacer(Modifier.height(14.dp))
        Text(body,color=Navy,fontSize=18.sp,lineHeight=29.sp)
        HorizontalDivider(Modifier.padding(top=25.dp),color=Color(0xFFE6E6E6))
    }
}

@Composable fun EmptyState(text:String)=Text(text,color=Gray,fontSize=18.sp)

@Composable fun SimplePage(title:String,content:@Composable ColumnScope.()->Unit) {
    Column(Modifier.fillMaxSize().background(Page).padding(27.dp)) {
        Text(title,color=Navy,fontSize=30.sp,fontWeight=FontWeight.Bold)
        Spacer(Modifier.height(22.dp))
        content()
    }
}
