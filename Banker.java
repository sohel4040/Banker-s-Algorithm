import java.io.*;

public class Banker
{
   public static void main(String a[]) throws Exception
   { 
       // Resources info
      
      int nres;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Enter number of Resources  : ");
      nres=Integer.parseInt(br.readLine());
      String rnm[]=new String[nres];
      int rin[]=new int[nres];
      int avain[]=new int[nres];
      if(nres>0)
      {

       for(int i=0;i<nres;i++)
       {
           System.out.println("Enter name of Resource "+(i+1)+" : ");
           rnm[i]=br.readLine();
           System.out.println("Enter Instances of "+rnm[i]+" Resource : ");
           rin[i]=Integer.parseInt(br.readLine());
           avain[i]=rin[i];
       }

      }
      else
      {
         System.out.println("Number of resources is must greater than 0");
         System.exit(0);

      }
      //Processes info
      int npr;
     
      System.out.println("Enter number of process");
      npr=Integer.parseInt(br.readLine());
      String pnm[]=new String[npr];
      int ain[][]=new int[npr][nres];
      int maxin[][]=new int[npr][nres];
      int needin[][]=new int[npr][nres];
      for(int i=0;i<npr;i++)
       {
           System.out.println("Enter name of Process "+(i+1)+" : ");
           pnm[i]=br.readLine();
   
            System.out.println("For Process : "+pnm[i]);
           for(int j=0; j<nres; j++)
           {
               
              
               System.out.println("Resource Name : "+rnm[j]);

                    System.out.println("Enter Maximum Instances : ");
                    maxin[i][j]=Integer.parseInt(br.readLine());
                    if(maxin[i][j] > rin[j])
                    {
                        System.out.println("Max Instances should not be greater than Resource Instances");
                        System.exit(0);
                    }
                    System.out.println("Enter Allocated Instances : ");
                    ain[i][j]=Integer.parseInt(br.readLine());
                    if(avain[j]>=ain[i][j])
                    {
                        avain[j]=avain[j]-ain[i][j];
                    }
                    else{
                        System.out.println("Available instances should be greater than allocated instances");
                        System.exit(0);
                    }
                    needin[i][j]=maxin[i][j]-ain[i][j];
                    if(needin[i][j] < 0)
                    {
                        System.out.println("Need should be greater than zero");
                        System.exit(0);
                    }

           }
           System.out.println("__________________________________________________ \n");
       } 
       
    
   
        for(int j=0;j<nres;j++)
        {
        System. out.println("available : of "+rnm[j]+" res = "+avain[j]);
        }
        
        //1. if need < available
        //2.then  available = available + allocated
        // move the current process to safe sequence and set it true
        //3. else 
        // 4.move to next process 
        // 5.repeat 1 for next process  
        
        String seq[] = new String [npr];
        int tmp=0,exe=0,rok=0,pok=0;
        boolean b=false;
        boolean pass[]=new boolean[npr];
        

        while(pok<=npr)
            {
                pok++;
                for(int i=0;i<npr;i++)
                {
                        for(int j=0;j<nres;j++)
                        {
                            if(needin[i][j]<= avain[j])
                            {
                                rok++;
                            }
                        }
                    
                        if(rok==nres)
                        {
                            if(pass[i]==false)
                            {
                                pass[i]=true;
                                for(int j=0;j<nres;j++)
                                {
                                avain[j]=avain[j]+ain[i][j];
                                }
                                seq[i]=pnm[i];   
                            } 
                        }
                        else
                        {
                            b=true;
                        }
                         rok=0;
                         
                }
                
                
                if(b)
                {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("!!  Deadlock will be occured !!");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    break; 
                }    
            }
        if(!b)
        {
                    System.out.println("*******************************");
                    System.out.println("**  System is in safe state  **");
                    System.out.println("*******************************");
        }
         int ch;
                   do{
                   System.out.println("1: Safe Sequence ");
                   System.out.println("2: Available Instances ");
                   System.out.println("0: Exit ");
                   System.out.println("Enter your choice : ");
                    ch=Integer.parseInt(br.readLine());
                    
                    switch(ch)
                        {
                            case 0:
                                  break;
                            case 1 :
                               
                                System.out.println("Executed Processes Sequence: ");
                                for(int j=0;j<seq.length;j++)
                                {
                                    if(!(seq[j]==null))
                                    System.out.println(seq[j]);
                                }     
                                               
                                break;
                            case 2 :
                                System.out.println("Available Instances :");
                                for(int j=0;j<nres;j++)
                                {
                                     System.out.println(rnm[j]+" : "+avain[j]);
                                }
                                break;
                            default:
                                System.out.println("INVALID CHOICE");
                        }
                   }while(ch!=0);

   }
}
