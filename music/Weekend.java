package music;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Weekend {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// 1. 파일객체
		File file = new File("2.Weekend.wav");
		// 2. 오디오 입력객체
		AudioInputStream  audioStream = AudioSystem.getAudioInputStream(file);
		
		// 3. 파일 실행용으로 ‘Clip’ 객체 생성
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
														//clip.loop(clip.LOOP_CONTINUOUSLY);  -> 무한반복 기능
		
		
														// 볼륨조절 클라스 , 클라스 명이 Float 인 만큼  0~1사이 실수들 사이에서만 가능.
														FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
													    double percent = 0.15;   
													    float dB = (float) (Math.log(percent) / Math.log(10.0) * 20.0);
													    volume.setValue(dB);
	    
	    
		// 4. 스위치 key 입력용 scanner  및 스위치 작성
		Scanner scanner = new Scanner(System.in);
		
		//  'response' 라는 key 기본값 선언 
		String response = "";
		
		while( !response.equals("Q") )  {
			System.out.println("P = Play, S = Stop, R = Reset, Q = Quit");
			
			System.out.println("Enter your choice : ");
				response = scanner.next();
				
				// 소문자로 입력해도 대문자로 인식
				response = response.toUpperCase();
				
			switch ( response ) {
			case "P":
					clip.start();	
				break;
			case "S":
					clip.stop();
				break;
			case "R":
				clip.setMicrosecondPosition(0);
				break;
			case "Q":
					clip.close();
				break;

			default: System.out.println("Not a valid response");
				
			}
				
		} System.out.println("Bye~!");
		
	}

}
