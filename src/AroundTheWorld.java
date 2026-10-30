

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class AroundTheWorld {

	public static void main(String[] args) {
		// TODO: phương thức tự động sinh
		DataBase db = new DataBase();
		CountryView cv = new CountryView();
		Scanner sc = new Scanner(System.in);
		Quiz q = new Quiz();
		while (true) {
			System.out.println("1:一覧" + "2:詳細" + "3:検索" + "4:クイズ" + "0:終了");
			int n = sc.nextInt();
			if (n == 1) {
				System.out.println("---");
				ArrayList<Country> countries = db.getcountries();
				cv.showList(countries);

			} else if (n == 2) {
				ArrayList<Country> countries = db.getcountries();
				System.out.println("NOを入力してください");

				int no = sc.nextInt();
				cv.showDetail(countries.get(no - 1));

			} else if (n == 3) {
				ArrayList<Country> countries = db.getcountries();
				sc.nextLine();
				System.out.println("Keyword: ");
				String search = sc.nextLine();
				cv.search(countries, search);

			} else if (n == 4) {

				ArrayList<Country> countries = db.getcountries();
				q.startQuiz(countries, sc);

			} else if (n == 0) {
				System.out.println("終了");
				break;
			}

		}

	}

}

class Quiz {
	public void startQuiz(ArrayList<Country> countries, Scanner sc) {

		Random r = new Random();
		Country answer = countries.get(r.nextInt(countries.size()));

		// Tạo danh sách các lựa chọn (đáp án)
		ArrayList<Country> choices = new ArrayList<Country>();
		choices.add(answer);

		while (choices.size() < 4) {
			Country cn = countries.get(r.nextInt(countries.size()));

			boolean exists = false;
			for (Country x : choices) {
				if (x.getCountry().equals(cn.getCountry())) {
					exists = true;
					break;
				}
			}

			if (!exists) {
				choices.add(cn);
			}
		}

		// Xáo trộn thứ tự các lựa chọn
		Collections.shuffle(choices);

		// Hiển thị câu hỏi
		System.out.println("====== クイズ ======");
		System.out.println("「" + answer.getFood() + "」はどこの国の食べ物ですか？");

		// Hiển thị hình ảnh món ăn (mở bằng trình xem ảnh mặc định)
		showImage(answer.getImage());

		for (int i = 0; i < choices.size(); i++) {
			System.out.println((i + 1) + " : " + choices.get(i).getCountry());
		}

		System.out.print("答え：");
		int num = sc.nextInt();

		if (choices.get(num - 1).getCountry().equals(answer.getCountry())) {
			System.out.println("〇 正解！");
		} else {
			System.out.println("× 不正解");
			System.out.println("正解は " + answer.getCountry() + " です。");
		}
	}

	// Mở ảnh trong thư mục img bằng trình xem ảnh mặc định của máy
	private void showImage(String imageName) {
		if (imageName == null || imageName.isEmpty()) {
			System.out.println("(この国には画像が登録されていません)");
			return;
		}
		try {
			File imgFile = new File("img/" + imageName);
			if (!imgFile.exists()) {
				System.out.println("(画像が見つかりません: " + imgFile.getPath() + ")");
				return;
			}
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(imgFile);
			} else {
				System.out.println("(このPCでは画像を自動的に開けません: " + imgFile.getPath() + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class DataBase {
	public ArrayList<Country> getcountries() {
		ArrayList<Country> countries = new ArrayList<Country>();
		try {
			FileReader fr = new FileReader("countries.csv");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue; // Bỏ qua dòng trống
				}
				String[] data = line.split(",");
				Country c = new Country();
				c.setCountryId(Integer.parseInt(data[0].trim()));
				c.setCountry(data[1].trim());
				c.setCapital(data[2].trim());
				c.setFood(data[3].trim());
				c.setTraditional(data[4].trim());
				if (data.length > 5) {
					c.setImage(data[5].trim());
				}
				countries.add(c);

			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: xử lý ngoại lệ (exception)
		}
		return countries;
	}

}

class CountryView {
	public void showList(ArrayList<Country> countries) {
		for (int i = 0; i < countries.size(); i++) {
			Country c = countries.get(i);
			System.out.println("------");
			System.out.println("[Id] " + (i + 1));
			System.out.println("[COUNTRY] " + c.getCountry());

		}
	}

	public void showDetail(Country c) {
		System.out.println("---------");
		System.out.println("[ID] " + c.getCountryId());
		System.out.println("[Country] " + c.getCountry());
		System.out.println("[Capital] " + c.getCapital());
		System.out.println("[Food] " + c.getFood());
		System.out.println("[Traditional] " + c.getTraditional());

	}

	public void search(ArrayList<Country> countries, String word) {
		int index = 1;
		for (Country c : countries) {
			if (c.getCountry().toLowerCase().contains(word.toLowerCase())
					|| c.getFood().toLowerCase().contains(word.toLowerCase())) {
				System.out.println("[COUNTRY]: " + c.getCountry());
				System.out.println("[FOOD]: " + c.getFood());
				index++;
			}

		}
	}
}

class Country {
	private int countryId;
	private String country;
	private String capital;
	private String food;
	private String traditional;
	private String image;

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getTraditional() {
		return traditional;
	}

	public void setTraditional(String traditional) {
		this.traditional = traditional;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}