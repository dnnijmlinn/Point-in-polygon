package tehtava;
import java.io.*;
import java.util.ArrayList;



public class Pisteen_sijainti{
 
	
	// Tietäen kolme pistettä p, q, r, toiminto tarkastukset
	// kohta q sijaitsee linjan segmentin "pr"
	
    public static boolean onSegment(Pisteet p, Pisteet q, Pisteet r){
    	
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x)
        		&& q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            	return true;
        return false;
    }
    
    
	 // Etsi järjestetyn vektorin suunta (p, q, r).
	 // Funktio palauttaa seuraavat arvot
	 // 0 - > p, q ja r ovat kollineaarisia
	 // 1 - > myötäpäivään
	 // 2 - > vastapäivään
    
    public static int orientation(Pisteet p, Pisteet q, Pisteet r){
    	
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0)
            return 0;	//kollineaarinen
        	return (val > 0) ? 1 : 2;	// kello tai vastapäivään
    }
    
	 // Funktio, joka palauttaa true, jos viivasegmentti "p1q1"
	 // ja "p2q2" leikkaavat toisensa.
    
    public static boolean doIntersect(Pisteet p1, Pisteet q1, Pisteet p2, Pisteet q2){
 
    	// Löydämme neljä suuntaukset tarpeen yleisen ja

    	// Erikoistapaus
    	
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
 
     // Yleinen asia
        if (o1 != o2 && o3 != o4)
            return true;
        
     // Erikoistapaus
     // p1, q1 ja p2 ovat collinear, ja p2 sijaitsee segmentin p1q1
        if (o1 == 0 && onSegment(p1, p2, q1))
            return true;
 
     // p1, q1 ja p2 ovat collinear, ja q2 sijaitsee segmentin p1q1
        if (o2 == 0 && onSegment(p1, q2, q1))
            return true;
 
     // p2, q2 ja p1 ovat kollineaarisia, ja P1 sijaitsee segmentillä p2q2
        if (o3 == 0 && onSegment(p2, p1, q2))
            return true;
 
     // p2, q2 ja q1 ovat kollineaarisia, ja q1 sijaitsee segmentillä p2q2
        if (o4 == 0 && onSegment(p2, q1, q2))
            return true;
 
        	return false;	// Ei kuulu mihinkään edellä mainituista tapauksista
    }
 
    // Palauttaa true jos kohta p piilee sisällä monikulmio [] kanssa n vertices
    public static boolean isInside(Pisteet polygon[], int n, Pisteet p){
    	

    	// Monikulmiolla on oltava vähintään 3 kärkipistettä []
        int INF = 10000;
        if (n < 3)
            return false;
 
     // Create a point for the segment from p to infinity
        Pisteet extreme = new Pisteet(INF, p.y);
 
     // Laske edellä olevan suoran risteykset monikulmion sivujen kanssa
        int count = 0, i = 0;
        do {
        	
            int next = (i + 1) % n;
            
            
         // Tarkista, jos segmentti " P " ja "extreme" intersects

         // janalla 'monikulmio [I]' - 'monikulmio [seuraava]'
            if (doIntersect(polygon[i], polygon[next], p, extreme)){
            	
            	
            	// Jos piste " p "on kollineaarinen janan" I-next " kanssa,

            	// sitten Tarkistamme, onko se sijaitsee segmentin. Jos se on valhe, palauta totuus,

            	// muuten epätosi
                if (orientation(polygon[i], p, polygon[next]) == 0)
                    return onSegment(polygon[i], p, polygon[next]);
 
                	count++;
            }
            
            i = next;
        } while (i != 0);
 
     // Return true jos kreivi on pariton, muuten epätosi
        return (count & 1) == 1 ? true : false;
    }
    
 
    
 // Ohjainohjelma edellä mainittujen toimintojen tarkistamiseksi
    public static void main(String args[]) throws NumberFormatException, IOException{
    	
    	//lisätään arkki, johon kaikki ehdotukset tallennetaan tulostusta varten
    	ArrayList<String> lause = new ArrayList<String>();

    	//Ensin tarkistetaan, että se toimii mekaanisesti. lisää sitten arvot luetteloon
    	
        /*Pisteet polygon[] = { new Pisteet(0, 0), new Pisteet(10, 0),
                new Pisteet(10, 10), new Pisteet(0, 10) }; 
        int n = 4; */
    	
        
        //tiedoston lukeminen polygoni.txt        
        File polygoni = new File("polygoni.txt");
               
            BufferedReader brpolygoni = new BufferedReader(new FileReader(polygoni));
                String linepolygoni;
                
                //jaan jokaisen numeron pilkulla
                while ((linepolygoni = brpolygoni.readLine()) != null) {
                    String[] valpolygoni = linepolygoni.split(",");
                    
            /*      int k = 0;
                    while( k < valpolygoni.length) {
                    	int x = Integer.parseInt(valpolygoni[k]);
                    	System.out.println(x);  
                    	k++;
                    }		*/
                    
                    //määritä muuttuja jokaiselle numerolle
                    int a = Integer.parseInt(valpolygoni[0]);
                    int b = Integer.parseInt(valpolygoni[1]);
                    int c = Integer.parseInt(valpolygoni[2]);
                    int d = Integer.parseInt(valpolygoni[3]);
                    int e = Integer.parseInt(valpolygoni[4]);
                    int f = Integer.parseInt(valpolygoni[5]);
                    int j = Integer.parseInt(valpolygoni[6]);
                    int h = Integer.parseInt(valpolygoni[7]);

                    //point x,y
                    Pisteet polygon[] = { new Pisteet(a, b), new Pisteet(c, d),
                            new Pisteet(e, f), new Pisteet(j, h) }; 
                    int n = 4;                              
                    
     //Teen saman tiedostolle, jossa on pistekoordinaatit pisteet.txt
      File pisteet = new File("pisteet.txt");
                    
         	BufferedReader brpisteet = new BufferedReader(new FileReader(pisteet));
                    String linepisteet;
                        
                    while ((linepisteet = brpisteet.readLine()) != null) {
                    	String[] valpisteet = linepisteet.split(",");
                        int pistex = Integer.parseInt(valpisteet[0]);
                        int pistey = Integer.parseInt(valpisteet[1]);
                       
                        //pisteen koordinaatit
                        Pisteet p = new Pisteet(pistex, pistey);                    
                    
                    //Kirjoitan tiedot tiedostoon selvitys.txt
                        try(FileWriter writer = new FileWriter("selvitys.txt", false)){
                       // tallennus koko rivi
                        	String text = ("Piste(" + p.x + ", " + p.y + ") sijaitsee monikulmion sisällä: " + isInside(polygon, n, p));
                        	writer.write(text);
                        	lause.add(text + '\n');
                        // kirjoittaminen merkkien mukaan
                        	writer.append('\n');
                         
                        	writer.flush();
                        } catch(IOException ex){
                        	System.out.println(ex.getMessage());
                        } 
                        //tekstin kirjoittaminen virtaan
                        BufferedWriter writer = new BufferedWriter(new FileWriter("selvitys.txt", false));
                    for (int i = 0; i < lause.size(); i++) {
                        writer.write(lause.get(i) + "\n");
                    }

                writer.close();
                
	         }
                        break;
                }
                //Laitoin sen tulostaa tarkistaa kaikki tarjoukset
                System.out.print(lause);
                							
    }
}

