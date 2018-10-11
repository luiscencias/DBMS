#include <iostream>
#include <stdio.h>
#include <vector>
#include <string>
#include <cstring>
#include <stdlib.h>
#include <stack>
#include <queue>
#include <fstream>
#include <sstream>
#include <ctype.h>
#include <iterator>

using namespace std;

struct big_stuff{
	string subj, crn, crse, sec, cmp, cred, title, days, time, cap, act, rem, ints;
	//loc? bld, room
};

vector<big_stuff> biige;

template<typename Out>
void split(const std::string &s, char delim, Out result) {
    std::stringstream ss(s);
    std::string item;
    while (std::getline(ss, item, delim)) {
        *(result++) = item;
    }
}

std::vector<std::string> split(const std::string &s, char delim) {
    std::vector<std::string> elems;
    split(s, delim, std::back_inserter(elems));
    return elems;
}

string remove_syllabus(string s){
	if(s[s.size()-1] == ')' || s[s.size()-2] == ')'){
		return s.substr(0,s.size()-11);
	}
	return s;
}

void convert(){
	ofstream file ("class.db");
	file << "R	Schedule\n";
	file << "O	Subject,4#CRN,0#Course,0#Section,0#Cmp,2#Credits,0#Name,32#";
	file << "Days,5#Time,32#Capacity,0#Actual,0#Remaining,0#Instructor,32#\n";
	file << "P	#\n";
	for(int k = 0; k < biige.size(); k++){
		file << "T\t";
		file << biige[k].subj << "#";
		file << biige[k].crn << "#";
		file << biige[k].crse << "#";
		file << biige[k].sec << "#";
		file << biige[k].cmp << "#";
		file << biige[k].cred << "#";
		file << biige[k].title << "#";
		file << biige[k].days << "#";
		file << biige[k].time << "#";
		file << biige[k].cap << "#";
		file << biige[k].act << "#";
		file << biige[k].rem << "#";
		file << biige[k].ints << "#";
		file << endl;
	}
	file.close();
}

void process(string a, string b){
	big_stuff stuff;
	vector<string> slpi = split(a,',');
	stuff.crn = slpi[0];
	stuff.subj = slpi[1];
	stuff.crse = slpi[2];
	stuff.sec = slpi[3];
	stuff.cmp = slpi[4];
	stuff.cred = slpi[5];
	stuff.title = remove_syllabus(slpi[6]);
	for(int k = 0; k < slpi.size(); k++){
		//cout << remove_syllabus(slpi[k]) << "|_|";
	}
	//cout << endl;
	///////////////////////////////////////
	vector<string> ilps = split(b,',');
	stuff.days = ilps[1];
	stuff.time = ilps[2];
	stuff.cap = ilps[3];
	stuff.act = ilps[4];
	stuff.rem = ilps[5];
	stuff.ints = ilps[6].substr(0,ilps[6].size()-3);
	for(int k = 1; k < ilps.size(); k++){
		//cout << ilps[k] << "|_|";
	}
	//cout << endl;
	biige.push_back(stuff);
	//cout << a << endl << b << endl;
}



int main(){
	ifstream csv("csv.txt");
	string line, l2;
	while(getline(csv, line)){
		if(isdigit(line[0])){
			getline(csv, l2);
			process(line,l2);
		}
	}
	convert();
	
	return 0;
}