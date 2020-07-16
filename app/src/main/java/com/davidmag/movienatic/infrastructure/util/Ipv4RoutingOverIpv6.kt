package com.davidmag.movienatic.infrastructure.util

import okhttp3.Dns
import java.net.Inet4Address
import java.net.InetAddress
import java.net.UnknownHostException

//Nome anterior: GfycatIpv4Dns

class Ipv4RoutingOverIpv6 : Dns {
    @Throws(UnknownHostException::class)
    override fun lookup(hostname: String): List<InetAddress> {
        //return if (true) {
            val addresses: Array<InetAddress> = InetAddress.getAllByName(hostname)

            // prefer IPv4; list IPv4 first
            val result: ArrayList<InetAddress> = ArrayList()
            for (address in addresses) {
                if (address is Inet4Address) {
                    result.add(address)
                }
            }
            for (address in addresses) {
                if (address !is Inet4Address) {
                    result.add(address)
                }
            }
            return result
//        } else {
//            Dns.SYSTEM.lookup(hostname)
//        }
    }
}