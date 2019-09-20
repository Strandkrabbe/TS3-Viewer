/*
 *  Copyright 2017 UNIQY.de
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package henning.data.ts3;

import java.util.*;

public class StringAnalystic {

	@Deprecated
	public static String[] getAtribut(String input, String name) {
		List<String> Entrys = new ArrayList<String>();
		int Leng = input.length();
		int Pos = 0;
		//M1 = Get Tag
		//M0 = Wait
		//M2 = Read Value
		byte Mode = 1;
		String ATag = "";
		String AValue = "";
		while (Pos < Leng) {
			String NST = String.valueOf(input.charAt(Pos));
			if (Mode == 0) {
				if (NST.equals(" ") | NST.equals("|")) {
					Mode = 1;
					ATag = "";
				}
			} else if (Mode == 1) {
				if (NST.equals("=")) {
					AValue = "";
					if (ATag.equals(name)) {
						Mode = 2;
					} else {
						Mode = 0;
					}
				} else if (NST.equals(" ")) {
					Mode = 0;
				} else {
					ATag = ATag + NST;
				}
			} else if (Mode == 2) {
				if (NST.equals(" ")) {
					Mode = 0;
					Entrys.add(AValue);
				} else if (NST.equals("|")) {
					Mode = 1;
					Entrys.add(AValue);
				} else {
					AValue = AValue + NST;
				}
			}
			Pos++;
		}
		return Entrys.toArray(new String[Entrys.toArray().length]);
	}

	public static List<Map<String, String>> convertMap(String input) {
		List<Map<String, String>> RE = new ArrayList<Map<String, String>>();
		Map<String, String> LastMap = new HashMap<String, String>();
		int Mode = 1;
		int Pos = 0;
		int Leng = input.length();
		String LastTag = "";
		String LastValue = "";
		while (Pos < Leng) {
			String NST = String.valueOf(input.charAt(Pos));
			if (Mode == 1) {
				if (NST.equals("=")) {
					Mode = 2;
				} else if (NST.equals(" ")) {
					LastMap.put(LastTag,"");
					LastTag = "";
					LastValue = "";
				} else if (NST.equals("|")) {
					LastMap.put(LastTag,"");
					LastTag = "";
					LastValue = "";
					RE.add(LastMap);
					LastMap = new HashMap<String, String>();
				} else {
					LastTag = LastTag + NST;
				}
			} else if (Mode == 2) {
				if (NST.equals(" ")) {
					LastMap.put(LastTag, LastValue);
					LastValue = "";
					LastTag = "";
					Mode = 1;
				} else if (NST.equals("|")) {
					LastMap.put(LastTag, LastValue);
					LastValue = "";
					LastTag = "";
					RE.add(LastMap);
					LastMap = new HashMap<String, String>();
					Mode = 1;
				} else {
					LastValue = LastValue + NST;
				}
			}
			Pos++;
		}
		if (LastValue == "") {
			LastMap.put(LastTag, null);
		} else {
			LastMap.put(LastTag, LastValue);
		}
		RE.add(LastMap);
//		try {
//			for (Map<String, String> e : RE) {
//				List<String> keys = new ArrayList<String>();
//				keys.addAll(e.keySet());
//				for (String k : keys) {
//					e.put(k, replaceSpacer(e.get(k)));
//				}
//			}
//		} catch (NullPointerException ex) {
//
//		}
		return RE;
	}

	public static String replaceSpacer(String input) {
		try	{
		String Out = "";
		int Pos = 0;
		boolean FormatationActiveString = false;
		while (Pos < input.length()) {
			String X = String.valueOf(input.charAt(Pos));
			if (X.equals("\\")) {
				if (String.valueOf(input.charAt(Pos + 1)) != null) {
					if (String.valueOf(input.charAt(Pos + 1)).equals("s")) {
						Out = Out + " ";
						Pos++;
					} else if (String.valueOf(input.charAt(Pos + 1)).equals("p")) {
						Out = Out + "|";
						Pos++;
					} else if (String.valueOf(input.charAt(Pos + 1)).equals("n")) {
						Out = Out + "\n";
						Pos++;
					} else if (String.valueOf(input.charAt(Pos + 1)).equals("/")) {
						Out = Out + "/";
						Pos++;
					} else if (String.valueOf(input.charAt(Pos + 1)).equals("\\")) {
						Out = Out + "\\";
						Pos++;
					} else {
						Out = Out + "?";
						Pos++;
					}
				}
			} else if (X.equals("[")) {
				FormatationActiveString = true;
			} else if (X.equals("]")) {
				FormatationActiveString = false;
			} else if (FormatationActiveString == true) {

			} else {
				Out = Out + X;
			}
			Pos++;
		}
		return Out;
		}	catch (NullPointerException ex)	{
			return null;
		}
	}

	public static Map<String, String> getMap(List<Map<String, String>> src, String tag, String value) {
		int S = src.size();
		Map<String, String> RE = null;
		for (int C = 0; C < S && RE == null; C++) {
			if (src.get(C).get(tag).equals(value)) {
				RE = src.get(C);
			}
		}
		return RE;
	}

	public static List<String> getServergroups(String groupString) {
		List<String> AG = new ArrayList<String>();
		String ga = "";
		for (int C = 0; C < groupString.length(); C++) {
			String AS = String.valueOf(groupString.charAt(C));
			if (AS.equals(",")) {
				AG.add(String.valueOf(ga));
				ga = "";
			} else {
				ga = ga + AS;
			}
		}
		AG.add(String.valueOf(ga));
		return AG;
	}

	public static boolean hasServerGroup(List<String> input, String roleNum) {
		for (int C = 0; C < input.size(); C++) {
			if (input.get(C).equals(roleNum)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasServerGroup(String groupString, String groupID) {
		if (hasServerGroup(getServergroups(groupString), groupID)) {
			return true;
		}
		return false;
	}

	public static String replcesSpaceWithS(String in) {
		String newS = "";
		for (int C = 0; C < in.length(); C++) {
			String tmpS = String.valueOf(in.charAt(C));
			if (tmpS.equals(" ")) {
				newS = newS + "\\s";
			} else if (tmpS.equals("|")) {
				newS = newS + "\\p";
			} else if (tmpS.equals("\n")) {
				newS = newS + "\\n";
			} else if (tmpS.equals("/")) {
				newS = newS + "\\/";
			} else if (tmpS.equals("\\")) {
				newS = newS + "\\" + "\\";
			} else {
				newS = newS + tmpS;
			}
		}
		return newS;
	}

	public static String mapToString(List<Map<String, String>> input) {
		String tmpS = "";
		for (int C1 = 0; C1 < input.size(); C1++) {
			Map<String, String> aMap = input.get(C1);
			Set<String> ks = aMap.keySet();
			String[] S = ks.toArray(new String[ks.size()]);
			if (S.length < 1)	continue;
			for (int C2 = 0; C2 < S.length; C2++) {
				if (S[C2].length() == 0)	continue;
				
				String v = "";
				if (aMap.get(S[C2]) != null)	{
//					v = "=" + replcesSpaceWithS(aMap.get(S[C2]));
					v = "=" + aMap.get(S[C2]);
				}
				if (C2 < S.length - 1) {
					tmpS = tmpS + S[C2] + v + " ";
				} else {
					tmpS = tmpS + S[C2] + v;
				}
			}
			if (C1 < input.size() - 1) {
				tmpS = tmpS + "|";
			}
		}
		if (tmpS.endsWith("|") || tmpS.endsWith(" "))	{
			tmpS = tmpS.substring(0, tmpS.length() - 1);
		}
		return tmpS;
	}

	public static int timeMsToHours(long ms) {
		return (int) (ms / 1000 / 60 / 60);
	}

	public static String randomToken(short leng) {
		String[] allowedValues = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
				, "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		String random = "";
		for (int C = 0; C < leng; C++) {
			double R = Math.random();
			R = R * (allowedValues.length - 0.01);
			random = random + allowedValues[(int) Math.floor(R)];
		}
		return random;
	}

	public static String cutN(String tc) {
		String fin = "";
		for (int C = 0; C < tc.length(); C++) {
			if (!String.valueOf(tc.charAt(C)).equals("\n") && !String.valueOf(tc.charAt(C)).equals("\r") && !(tc.charAt(C) == 10)) {
				fin = fin + String.valueOf(tc.charAt(C));
			}
		}
		return fin;
	}

	public static boolean nonerrorString(String i) {
		try {
			if (i.contains("error id=0")) {
				return true;
			}
		} catch (NullPointerException ex) {

		}
		return false;
	}

	public static boolean permError(String i) {
		try {
			if (i.contains("error id=256")) {
				return true;
			}
		} catch (NullPointerException ex) {

		}
		return false;
	}

	public static String getFirstTag(String in) {
		in = cutN(in);
		if (in.contains(" ")) {
			return in.substring(0, in.indexOf(" "));
		} else {
			return in;
		}
	}
	
	public static String cutError(String in)	{
		return in.substring(0,in.indexOf("error id="));
	}
}