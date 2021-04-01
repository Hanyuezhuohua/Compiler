declare i32 @_str_length(i8* %0)
declare i8* @_str_substring(i8* %0, i32 %1, i32 %2)
declare i32 @_str_parseInt(i8* %0)
declare i32 @_str_ord(i8* %0, i32 %1)
declare i8* @_str_concat(i8* %0, i8* %1)
declare i1 @_str_eq(i8* %0, i8* %1)
declare i1 @_str_ne(i8* %0, i8* %1)
declare i1 @_str_lt(i8* %0, i8* %1)
declare i1 @_str_gt(i8* %0, i8* %1)
declare i1 @_str_le(i8* %0, i8* %1)
declare i1 @_str_ge(i8* %0, i8* %1)
declare void @_gbl_print(i8* %0)
declare void @_gbl_println(i8* %0)
declare void @_gbl_printInt(i32 %0)
declare void @_gbl_printlnInt(i32 %0)
declare i8* @_gbl_getString()
declare i32 @_gbl_getInt()
declare i8* @_gbl_toString(i32 %0)
declare i8* @malloc(i32 %0)
@INF = common global i32 zeroinitializer, align 4
@n = common global i32 zeroinitializer, align 4
@m = common global i32 zeroinitializer, align 4
@a = common global i32** zeroinitializer, align 4
@str0 = private unnamed_addr constant [2 x i8] c"-1", align 1
@str1 = private unnamed_addr constant [1 x i8] c" ", align 1
@str2 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	store i32 10000000, i32* @INF, align 4
	ret void 
}
define void @init()

0:
;prev: 
;next: 15 
	%1 = alloca i32, align 4
	%2 = alloca i32, align 4
	%3 = alloca i32, align 4
	%4 = alloca i32, align 4
	%5 = alloca i32, align 4
	store void , i32* %5, align 0
	store void , i32* %4, align 0
	store void , i32* %3, align 0
	store void , i32* %2, align 0
	store void , i32* %1, align 0
	%6 = call i32 @_gbl_getInt()
	store i32 %6, i32* @n, align 4
	%7 = call i32 @_gbl_getInt()
	store i32 %7, i32* @m, align 4
	%8 = load i32, i32* @n, align 4
	%9 = mul i32 %8, 4
	%10 = add i32 %9, 4
	%11 = call i8* @malloc(i32 %10)
	%12 = bitcast i8* %11 to i32*
	store i32 %8, i32* %12, align 4
	%13 = getelementptr inbounds i32, i32* %12, i32 1
	%14 = bitcast i32* %13 to i32**
	br label %15
15:
;prev: 0 18 
;next: 18 27 
;iDom: 0
;DomFrontiers: 15 
	%16 = phi i32 [ 0, %0 ], [ %26, %18 ]
	%17 = icmp slt i32 %16, %8
	br i1 %17, label %18, label %27
18:
;prev: 15 
;next: 15 
;iDom: 15
;DomFrontiers: 15 
	%19 = getelementptr inbounds i32*, i32** %14, i32 %16
	%20 = load i32, i32* @n, align 4
	%21 = mul i32 %20, 4
	%22 = add i32 %21, 4
	%23 = call i8* @malloc(i32 %22)
	%24 = bitcast i8* %23 to i32*
	store i32 %20, i32* %24, align 4
	%25 = getelementptr inbounds i32, i32* %24, i32 1
	store i32* %25, i32** %19, align 4
	%26 = add i32 %16, 1
	br label %15
27:
;prev: 15 
;next: 28 
;iDom: 15
	store i32** %14, i32*** @a, align 4
	store i32 0, i32* %1, align 4
	br label %28
28:
;prev: 27 62 
;next: 32 33 
;iDom: 27
;DomFrontiers: 28 
	%29 = load i32, i32* %1, align 4
	%30 = load i32, i32* @n, align 4
	%31 = icmp slt i32 %29, %30
	br i1 %31, label %32, label %33
32:
;prev: 28 
;next: 34 
;iDom: 28
;DomFrontiers: 28 
	store i32 0, i32* %2, align 4
	br label %34
33:
;prev: 28 
;next: 38 
;iDom: 28
	store i32 0, i32* %1, align 4
	br label %38
34:
;prev: 32 59 
;next: 42 50 
;iDom: 32
;DomFrontiers: 28 34 
	%35 = load i32, i32* %2, align 4
	%36 = load i32, i32* @n, align 4
	%37 = icmp slt i32 %35, %36
	br i1 %37, label %42, label %50
38:
;prev: 33 65 
;next: 51 58 
;iDom: 33
;DomFrontiers: 38 
	%39 = load i32, i32* %1, align 4
	%40 = load i32, i32* @n, align 4
	%41 = icmp slt i32 %39, %40
	br i1 %41, label %51, label %58
42:
;prev: 34 
;next: 59 
;iDom: 34
;DomFrontiers: 34 
	%43 = load i32, i32* %1, align 4
	%44 = load i32**, i32*** @a, align 4
	%45 = getelementptr inbounds i32*, i32** %44, i32 %43
	%46 = load i32, i32* %2, align 4
	%47 = load i32*, i32** %45, align 4
	%48 = getelementptr inbounds i32, i32* %47, i32 %46
	%49 = load i32, i32* @INF, align 4
	store i32 %49, i32* %48, align 4
	br label %59
50:
;prev: 34 
;next: 62 
;iDom: 34
;DomFrontiers: 28 
	br label %62
51:
;prev: 38 
;next: 65 
;iDom: 38
;DomFrontiers: 38 
	%52 = load i32, i32* %1, align 4
	%53 = load i32**, i32*** @a, align 4
	%54 = getelementptr inbounds i32*, i32** %53, i32 %52
	%55 = load i32, i32* %1, align 4
	%56 = load i32*, i32** %54, align 4
	%57 = getelementptr inbounds i32, i32* %56, i32 %55
	store i32 0, i32* %57, align 4
	br label %65
58:
;prev: 38 
;next: 68 
;iDom: 38
	store i32 0, i32* %1, align 4
	br label %68
59:
;prev: 42 
;next: 34 
;iDom: 42
;DomFrontiers: 34 
	%60 = load i32, i32* %2, align 4
	%61 = add i32 %60, 1
	store i32 %61, i32* %2, align 4
	br label %34
62:
;prev: 50 
;next: 28 
;iDom: 50
;DomFrontiers: 28 
	%63 = load i32, i32* %1, align 4
	%64 = add i32 %63, 1
	store i32 %64, i32* %1, align 4
	br label %28
65:
;prev: 51 
;next: 38 
;iDom: 51
;DomFrontiers: 38 
	%66 = load i32, i32* %1, align 4
	%67 = add i32 %66, 1
	store i32 %67, i32* %1, align 4
	br label %38
68:
;prev: 58 84 
;next: 72 83 
;iDom: 58
;DomFrontiers: 68 
	%69 = load i32, i32* %1, align 4
	%70 = load i32, i32* @m, align 4
	%71 = icmp slt i32 %69, %70
	br i1 %71, label %72, label %83
72:
;prev: 68 
;next: 84 
;iDom: 68
;DomFrontiers: 68 
	%73 = call i32 @_gbl_getInt()
	store i32 %73, i32* %3, align 4
	%74 = call i32 @_gbl_getInt()
	store i32 %74, i32* %4, align 4
	%75 = call i32 @_gbl_getInt()
	store i32 %75, i32* %5, align 4
	%76 = load i32, i32* %3, align 4
	%77 = load i32**, i32*** @a, align 4
	%78 = getelementptr inbounds i32*, i32** %77, i32 %76
	%79 = load i32, i32* %4, align 4
	%80 = load i32*, i32** %78, align 4
	%81 = getelementptr inbounds i32, i32* %80, i32 %79
	%82 = load i32, i32* %5, align 4
	store i32 %82, i32* %81, align 4
	br label %84
83:
;prev: 68 
;next: 
;iDom: 68
	ret void 
84:
;prev: 72 
;next: 68 
;iDom: 72
;DomFrontiers: 68 
	%85 = load i32, i32* %1, align 4
	%86 = add i32 %85, 1
	store i32 %86, i32* %1, align 4
	br label %68
}
define i32 @main()

0:
;prev: 
;next: 4 
	%1 = alloca i32, align 4
	%2 = alloca i32, align 4
	%3 = alloca i32, align 4
	store void , i32* %3, align 0
	store void , i32* %2, align 0
	store void , i32* %1, align 0
	call void @g_init()
	call void @init()
	store i32 0, i32* %3, align 4
	br label %4
4:
;prev: 0 26 
;next: 8 9 
;iDom: 0
;DomFrontiers: 4 
	%5 = load i32, i32* %3, align 4
	%6 = load i32, i32* @n, align 4
	%7 = icmp slt i32 %5, %6
	br i1 %7, label %8, label %9
8:
;prev: 4 
;next: 10 
;iDom: 4
;DomFrontiers: 4 
	store i32 0, i32* %1, align 4
	br label %10
9:
;prev: 4 
;next: 14 
;iDom: 4
	store i32 0, i32* %1, align 4
	br label %14
10:
;prev: 8 93 
;next: 18 19 
;iDom: 8
;DomFrontiers: 4 10 
	%11 = load i32, i32* %1, align 4
	%12 = load i32, i32* @n, align 4
	%13 = icmp slt i32 %11, %12
	br i1 %13, label %18, label %19
14:
;prev: 9 107 
;next: 20 21 
;iDom: 9
;DomFrontiers: 14 
	%15 = load i32, i32* %1, align 4
	%16 = load i32, i32* @n, align 4
	%17 = icmp slt i32 %15, %16
	br i1 %17, label %20, label %21
18:
;prev: 10 
;next: 22 
;iDom: 10
;DomFrontiers: 10 
	store i32 0, i32* %2, align 4
	br label %22
19:
;prev: 10 
;next: 26 
;iDom: 10
;DomFrontiers: 4 
	br label %26
20:
;prev: 14 
;next: 29 
;iDom: 14
;DomFrontiers: 14 
	store i32 0, i32* %2, align 4
	br label %29
21:
;prev: 14 
;next: 
;iDom: 14
	ret i32 0
22:
;prev: 18 110 
;next: 33 57 
;iDom: 18
;DomFrontiers: 22 10 
	%23 = load i32, i32* %2, align 4
	%24 = load i32, i32* @n, align 4
	%25 = icmp slt i32 %23, %24
	br i1 %25, label %33, label %57
26:
;prev: 19 
;next: 4 
;iDom: 19
;DomFrontiers: 4 
	%27 = load i32, i32* %3, align 4
	%28 = add i32 %27, 1
	store i32 %28, i32* %3, align 4
	br label %4
29:
;prev: 20 115 
;next: 58 68 
;iDom: 20
;DomFrontiers: 29 14 
	%30 = load i32, i32* %2, align 4
	%31 = load i32, i32* @n, align 4
	%32 = icmp slt i32 %30, %31
	br i1 %32, label %58, label %68
33:
;prev: 22 
;next: 70 92 
;iDom: 22
;DomFrontiers: 22 
	%34 = load i32, i32* %1, align 4
	%35 = load i32**, i32*** @a, align 4
	%36 = getelementptr inbounds i32*, i32** %35, i32 %34
	%37 = load i32, i32* %2, align 4
	%38 = load i32*, i32** %36, align 4
	%39 = getelementptr inbounds i32, i32* %38, i32 %37
	%40 = load i32, i32* %1, align 4
	%41 = load i32**, i32*** @a, align 4
	%42 = getelementptr inbounds i32*, i32** %41, i32 %40
	%43 = load i32, i32* %3, align 4
	%44 = load i32*, i32** %42, align 4
	%45 = getelementptr inbounds i32, i32* %44, i32 %43
	%46 = load i32, i32* %3, align 4
	%47 = load i32**, i32*** @a, align 4
	%48 = getelementptr inbounds i32*, i32** %47, i32 %46
	%49 = load i32, i32* %2, align 4
	%50 = load i32*, i32** %48, align 4
	%51 = getelementptr inbounds i32, i32* %50, i32 %49
	%52 = load i32, i32* %45, align 4
	%53 = load i32, i32* %51, align 4
	%54 = add i32 %52, %53
	%55 = load i32, i32* %39, align 4
	%56 = icmp sgt i32 %55, %54
	br i1 %56, label %70, label %92
57:
;prev: 22 
;next: 93 
;iDom: 22
;DomFrontiers: 10 
	br label %93
58:
;prev: 29 
;next: 96 98 
;iDom: 29
;DomFrontiers: 29 
	%59 = load i32, i32* %1, align 4
	%60 = load i32**, i32*** @a, align 4
	%61 = getelementptr inbounds i32*, i32** %60, i32 %59
	%62 = load i32, i32* %2, align 4
	%63 = load i32*, i32** %61, align 4
	%64 = getelementptr inbounds i32, i32* %63, i32 %62
	%65 = load i32, i32* %64, align 4
	%66 = load i32, i32* @INF, align 4
	%67 = icmp eq i32 %65, %66
	br i1 %67, label %96, label %98
68:
;prev: 29 
;next: 107 
;iDom: 29
;DomFrontiers: 14 
	%69 = getelementptr inbounds [0 x i8], [0 x i8]* @str2, i32 0, i32 0
	call void @_gbl_println(i8* %69)
	br label %107
70:
;prev: 33 
;next: 92 
;iDom: 33
;DomFrontiers: 92 
	%71 = load i32, i32* %1, align 4
	%72 = load i32**, i32*** @a, align 4
	%73 = getelementptr inbounds i32*, i32** %72, i32 %71
	%74 = load i32, i32* %2, align 4
	%75 = load i32*, i32** %73, align 4
	%76 = getelementptr inbounds i32, i32* %75, i32 %74
	%77 = load i32, i32* %1, align 4
	%78 = load i32**, i32*** @a, align 4
	%79 = getelementptr inbounds i32*, i32** %78, i32 %77
	%80 = load i32, i32* %3, align 4
	%81 = load i32*, i32** %79, align 4
	%82 = getelementptr inbounds i32, i32* %81, i32 %80
	%83 = load i32, i32* %3, align 4
	%84 = load i32**, i32*** @a, align 4
	%85 = getelementptr inbounds i32*, i32** %84, i32 %83
	%86 = load i32, i32* %2, align 4
	%87 = load i32*, i32** %85, align 4
	%88 = getelementptr inbounds i32, i32* %87, i32 %86
	%89 = load i32, i32* %82, align 4
	%90 = load i32, i32* %88, align 4
	%91 = add i32 %89, %90
	store i32 %91, i32* %76, align 4
	br label %92
92:
;prev: 33 70 
;next: 110 
;iDom: 33
;DomFrontiers: 22 
	br label %110
93:
;prev: 57 
;next: 10 
;iDom: 57
;DomFrontiers: 10 
	%94 = load i32, i32* %1, align 4
	%95 = add i32 %94, 1
	store i32 %95, i32* %1, align 4
	br label %10
96:
;prev: 58 
;next: 113 
;iDom: 58
;DomFrontiers: 113 
	%97 = getelementptr inbounds [2 x i8], [2 x i8]* @str0, i32 0, i32 0
	call void @_gbl_print(i8* %97)
	br label %113
98:
;prev: 58 
;next: 113 
;iDom: 58
;DomFrontiers: 113 
	%99 = load i32, i32* %1, align 4
	%100 = load i32**, i32*** @a, align 4
	%101 = getelementptr inbounds i32*, i32** %100, i32 %99
	%102 = load i32, i32* %2, align 4
	%103 = load i32*, i32** %101, align 4
	%104 = getelementptr inbounds i32, i32* %103, i32 %102
	%105 = load i32, i32* %104, align 4
	%106 = call i8* @_gbl_toString(i32 %105)
	call void @_gbl_print(i8* %106)
	br label %113
107:
;prev: 68 
;next: 14 
;iDom: 68
;DomFrontiers: 14 
	%108 = load i32, i32* %1, align 4
	%109 = add i32 %108, 1
	store i32 %109, i32* %1, align 4
	br label %14
110:
;prev: 92 
;next: 22 
;iDom: 92
;DomFrontiers: 22 
	%111 = load i32, i32* %2, align 4
	%112 = add i32 %111, 1
	store i32 %112, i32* %2, align 4
	br label %22
113:
;prev: 96 98 
;next: 115 
;iDom: 58
;DomFrontiers: 29 
	%114 = getelementptr inbounds [1 x i8], [1 x i8]* @str1, i32 0, i32 0
	call void @_gbl_print(i8* %114)
	br label %115
115:
;prev: 113 
;next: 29 
;iDom: 113
;DomFrontiers: 29 
	%116 = load i32, i32* %2, align 4
	%117 = add i32 %116, 1
	store i32 %117, i32* %2, align 4
	br label %29
}
